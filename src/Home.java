import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@MultipartConfig
@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class Home extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sav_dir="files";
        //String path = SSIServletRequestUtil.getRelativePath(req);
        PrintWriter out = response.getWriter();

        //out.print(request.getServletContext().getRealPath("resources"));

        //out.print(request.getServletContext().getResource("resources"));

        String extension = request.getParameter("selection-value");
        String filePath = request.getServletContext().getRealPath("resources") + File.separator + sav_dir;
        File file = new File(filePath);
        String fileName="";

        if(!file.exists()) {
            file.mkdir(); //Create file directory
        }

        if(extension.equals("java")){
            file_parsing(request,response,filePath);
        }else if(extension.equals("jar")){

        }else {
            Part zipfile = request.getPart("file");
            fileName = Paths.get(zipfile.getSubmittedFileName()).getFileName().toString();
            zipfile.write(filePath+File.separator+fileName);
            unZipFile(filePath+File.separator+zipfile.getSubmittedFileName(), filePath);
        }

        deleteDataFolder(new File(filePath +File.separator+ fileName), false);

        out.print(new File(filePath +File.separator+ fileName).toString());
        //response.sendRedirect("InfoPage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    private void createSLFile(InputStream fileContent, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileContent));
        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            out.print(line + "\n");
        }

    }

    private void file_parsing(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException, ServletException {

        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
        //String filenames = "";

        // Fore each file -> print file names to webpage
        for (Part filePart : fileParts) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            //InputStream fileContent = filePart.getInputStream();
            filePart.write(filePath+File.separator+fileName);
            //filenames += " " + fileName;
        }
    }

    private void unZipFile(String zipFilePath,String unzipLocation) throws IOException {
        Files.createDirectories(Paths.get(unzipLocation));

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            while (nextEntry != null) {
                Path filePath = Paths.get(unzipLocation, nextEntry.getName());
                if (!nextEntry.isDirectory()) {
                    unzipFiles(zipInputStream, filePath);
                } else {
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

    private void deleteDataFolder(File folder, boolean multiple) {
        File[] files = folder.listFiles();
        if(files!=null) {
            if(!multiple){
                folder.delete();
            }else {
                for (File f : files) {
                    if (f.isDirectory()) {
                        deleteDataFolder(f, true);
                    } else {
                        f.delete();
                    }
                }
            }
        }
        folder.delete();
    }

}
