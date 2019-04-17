import Management.ClassManagement;
import Management.FileTransfer;
import files.InfoExtraction;

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
    private ClassManagement classCreator;
    private ArrayList<String> javaFileNames;

    private String javaFilePath="",companyFilePath="";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        fileTransfer = new FileTransfer(request);
        javaFileNames = fileTransfer.getJavaFileNames();
        javaFilePath = fileTransfer.getJavaFilePath();
        companyFilePath = fileTransfer.getCompanyFilePath();
        classCreator = new ClassManagement(javaFilePath,javaFileNames);
        //classCreator.ClassCreation();
        InfoExtraction cd = new InfoExtraction(response);
        BufferedReader br;
        for(String s : javaFileNames){
            br = new BufferedReader(new FileReader(javaFilePath+File.separator+s+".java"));

            String line;

            while ((line = br.readLine()) != null) {
                cd.checkIfClassLine(line);
            }
            cd.showDetails();
            cd.ClearDetails();
            response.getWriter().println();
            response.getWriter().println();
            response.getWriter().println();
        }



        /*
        Class c = null;
        try {
            c = Class.forName("nosejob.Celsius");
        } catch (ClassNotFoundException e) {
            response.getWriter().println(e);
        }*/


        //classCreator.instantiateClass("Celsius", response);
        //response.getWriter().println(c.toString());
        //response.sendRedirect("dashboard.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

}
