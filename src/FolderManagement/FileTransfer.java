package FolderManagement;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.apache.commons.io.FileUtils.copyFileToDirectory;

public class FileTransfer {

    private String sav_dir="files";
    private String java_dir="java";
    private String class_dir="class";

    private ArrayList<String> filenames = new ArrayList<>();

    private Boolean javaFiles=false, classFiles=false;

    private String mainFilePath, javaFilePath, classFilePath, extension, fileName, mainName;

    private HttpServletRequest request;

    public FileTransfer(HttpServletRequest request) throws IOException, ServletException {
        this.request = request;
        extension = request.getParameter("selection-value");
        mainFilePath = request.getServletContext().getRealPath("resources") + File.separator + sav_dir;
        javaFilePath = request.getServletContext().getRealPath("resources") + File.separator + sav_dir + File.separator + java_dir;
        classFilePath = request.getServletContext().getRealPath("resources") + File.separator + sav_dir + File.separator + class_dir;

        fileCreator(); //Create file objects from paths
        fileTransferring();
        deleteDataFolder(new File(mainFilePath+File.separator + fileName));
        File[] zipFile = new File(mainFilePath+File.separator+mainName).listFiles();
        if(!(zipFile==null)){
            showFiles(zipFile);
        }

        deleteDataFolder(new File(mainFilePath+File.separator + mainName));
    }


    private void fileCreator(){
        File file = new File(mainFilePath);
        File fileJava = new File(javaFilePath);
        File fileClass = new File(classFilePath);
        if(!file.exists()) {
            file.mkdir();
        }
        if(!fileJava.exists()) {
            fileJava.mkdir();
        }
        if(!fileClass.exists()) {
            fileClass.mkdir();
        }
    }

    private void fileTransferring() throws IOException, ServletException {
        if(extension.equals(java_dir)){
            fileParsing(request,javaFilePath);
        }else {
            Part zipfile = request.getPart("file");
            fileName = getFileName(zipfile);
            zipfile.write(mainFilePath+File.separator+fileName);
            unZipFile(mainFilePath+File.separator+zipfile.getSubmittedFileName(), mainFilePath);
        }
    }

    private String getFileName(Part filePart){
        return Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
    }


    private void fileParsing(HttpServletRequest request, String filePath) throws IOException, ServletException {
        javaFiles=true;
        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
        for (Part filePart : fileParts) {
            fileName = getFileName(filePart);
            filePart.write(filePath+File.separator+fileName);
            addToFileNames(fileName);
        }
    }

    private void unZipFile(String zipFilePath,String unzipLocation) throws IOException {
        Files.createDirectories(Paths.get(unzipLocation));
        int iter=0;
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            while (nextEntry != null) {
                Path filePath = Paths.get(unzipLocation, nextEntry.getName());
                if (!nextEntry.isDirectory()) {
                    unzipFiles(zipInputStream, filePath);
                } else {
                    if(iter==0){
                        String path = filePath.toString();
                        mainName = path.substring(path.lastIndexOf("files" + File.separator)+"files".length()+1);
                        mainName = mainName.substring(0,mainName.indexOf(File.separator));
                        iter++;
                    }
                    Files.createDirectories(filePath);
                }
                zipInputStream.closeEntry();
                nextEntry = zipInputStream.getNextEntry();
            }
        }
    }
    private void unzipFiles(ZipInputStream zipInputStream,Path filePath) throws IOException {

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath.toAbsolutePath().toString()))) {
            byte[] bytesIn = new byte[1024];
            int len = 0;
            while ((len = zipInputStream.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, len);
            }
        }

    }

    private void deleteDataFolder(File folder){
        File[] files = folder.listFiles();
        if(files!=null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDataFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    private void showFiles(File[] files) throws IOException {
        for (File file : files) {
            if (file.isDirectory()) {
                File[] nullTestFiles = file.listFiles();
                if(!(nullTestFiles==null)){
                    showFiles(nullTestFiles);
                }
            } else {
                if(file.toString().contains(".java")){
                    javaFiles=true;
                    transferring(file, javaFilePath);
                    addToFileNames(file.toString());
                }else if(file.toString().contains(".class")){
                    classFiles=true;
                    transferring(file, classFilePath);
                    addToFileNames(file.toString());
                }
            }
        }
    }

    private void transferring(File file,String path){
        try {
            copyFileToDirectory(new File(file.getAbsolutePath()), new File(path));
        } catch (IOException e) {

        }
    }

    private void addToFileNames(String s){
        if(s.contains(File.separator)){
            s = s.substring(s.lastIndexOf(File.separator)+1, s.indexOf("."));
        }

        if(!filenames.contains(s)){
            filenames.add(s);
        }
    }

    public ArrayList<String> getfileNames(){
        return filenames;
    }

    public String getJavaFilePath(){
        return javaFilePath;
    }

    public String getClassFilePath(){
        return classFilePath;
    }

    public Boolean getBools(int i){
        if(i==1){
            return javaFiles;
        }else{
            return classFiles;
        }
    }
}
