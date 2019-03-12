import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class Home extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sav_dir="files";
        PrintWriter out = response.getWriter();
        int flag=0;
        //TODO account for when no test foler exists in this director
        String filePath = "C://test"  + File.separator + sav_dir;
        File file = new File(filePath);

        if(!file.exists()){
            file.mkdir(); //Create file directory
        }

        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
        String filenames = "";
        String line = "";
        BufferedReader bufferedReader = null;

        // Fore each file -> print file names to webpage
        for (Part filePart : fileParts) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(fileContent));

//            filePart.write(filePath+File.separator+fileName);
//            filenames += " " + fileName;
            while ((line = bufferedReader.readLine()) != null) {
                out.print(line + "\n");
            }
        }

        if(flag==1){
            out.print("success");
        }else{
            out.print(filenames);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

//    private void lineParser(BufferedReader br) {
//
//    }



}
