import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
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

        if(!file.exists()) {
            file.mkdir(); //Create file directory
        }

        if(extension.equals("java")){
            file_parsing(request,response,filePath);
        }else if(extension.equals("jar")){

        }else {
            String fileZip = "src/main/resources/unzipTest/compressed.zip";
            File destDir = new File("../resources/unzipTest");
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        }

        //deleteFolder(file);
        //out.print(file.toString());
        response.sendRedirect("InfoPage.jsp");
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

    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

}
