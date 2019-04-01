

import FolderManagement.FileTransfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

@MultipartConfig
@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class Home extends HttpServlet {

    private FileTransfer fileTransfer;
    private ArrayList<String> fileNames;

    private Boolean javaFilesSubmitted=false, classFilesSubmitted=false;

    private String javaFilePath="",classFilePath="";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        fileTransfer = new FileTransfer(request);
        fileNames = fileTransfer.getfileNames();
        response.getWriter().println("Files found:");
        for(String s: fileNames){
            response.getWriter().println(s);
        }
        javaFilesSubmitted = fileTransfer.getBools(1);
        classFilesSubmitted = fileTransfer.getBools(0);
        javaFilePath = fileTransfer.getJavaFilePath();
        classFilePath = fileTransfer.getClassFilePath();
        //response.sendRedirect("InfoPage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

}
