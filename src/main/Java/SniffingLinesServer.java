import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import files.*;
import management.FileTransfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet("/SniffingLinesServer")
public class SniffingLinesServer extends HttpServlet {

    private FileTransfer fileTransfer;
    private ArrayList<String> javaFileNames;
    private String javaFilePath="",companyFilePath="";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        fileTransfer = new FileTransfer(request, response);
        javaFileNames = fileTransfer.getfileNames();
        javaFilePath = fileTransfer.getJavaFilePath();
        //companyFilePath = fileTransfer.getCompanyFilePath();
        DataCollection dc = new DataCollection();

        ArrayList<SLVariable> variables = new ArrayList<>();
        ArrayList<SLMethod> methods = new ArrayList<>();
        ArrayList<SLClass> classes = new ArrayList<>();
        ArrayList<SLInterface> interfaces = new ArrayList<>();
        ArrayList<SLEnum> enums = new ArrayList<>();


        InputStream in = null;
        CompilationUnit cu = null;
        for(String s : javaFileNames) {

            try
            {
                in = new FileInputStream(javaFilePath + File.separator + s+".java");
                cu = JavaParser.parse(in);

                dc.classDetection(cu);
                dc.enumDetector(cu);
                dc.interfaceDetetection(cu);
                dc.variableDetection(cu);
                dc.methodDetection(cu);

            }finally
            {
                in.close();
            }

            /*
            response.getWriter().println("Classes:\n"+dc.getClasses());
            response.getWriter().println("Interfaces:\n"+dc.getInterfaces());
            response.getWriter().println();
            response.getWriter().println("Enums:\n" + dc.getEnums());
            response.getWriter().println("Methods");
            response.getWriter().println(dc.getMethods());
            response.getWriter().println("Variables:\n" + dc.getVariables());
            response.getWriter().println("Comment count:"+dc.commentCount(cu));
            */
            response.getWriter().println();
            response.getWriter().println();
            response.getWriter().println();
            variables=dc.getVariablesList();classes=dc.getClassList();interfaces=dc.getInterfaceList();enums=dc.getEnumList();methods=dc.getMethodList();

            response.getWriter().println("Classes");
            for(SLClass e: classes){
                response.getWriter().println(e.toString());
            }
            response.getWriter().println("Methods");
            for(SLMethod e: methods){
                response.getWriter().println(e.toString());
            }
            response.getWriter().println("Variables");
            for(SLVariable e: variables){
                response.getWriter().println(e.toString());
            }
            response.getWriter().println("Enums");
            for(SLEnum e: enums){
                response.getWriter().println(e.toString());
            }
            response.getWriter().println("Interfaces");
            for(SLInterface e: interfaces){
                response.getWriter().println(e.toString());
            }

            /*
            response.getWriter().println("Variables empty:" + variables.isEmpty());
            response.getWriter().println("methods empty:" + methods.isEmpty());
            response.getWriter().println("interfaces empty:" + interfaces.isEmpty());
            response.getWriter().println("enums empty:" + enums.isEmpty());
            response.getWriter().println("classes empty:" + classes.isEmpty());
            */

            dc.clearAll();

        }
        //response.sendRedirect("dashboard.jsp");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}