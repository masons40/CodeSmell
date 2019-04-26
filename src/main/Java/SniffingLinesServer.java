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

        ArrayList<SLFile> files = new ArrayList<>();

        InputStream in = null;
        CompilationUnit cu = null;
        for(String s : javaFileNames) {
            ArrayList<SLVariable> variables = new ArrayList<>();
            ArrayList<SLMethod> methods = new ArrayList<>();
            ArrayList<SLClass> classes = new ArrayList<>();
            ArrayList<SLInterface> interfaces = new ArrayList<>();
            ArrayList<SLEnum> enums = new ArrayList<>();

            try
            {
                in = new FileInputStream(javaFilePath + File.separator + s+".java");
                cu = JavaParser.parse(in);

                dc.classDetection(cu);
                dc.enumDetector(cu);
                dc.interfaceDetetection(cu);
                dc.variableDetection(cu);
                dc.methodDetection(cu);

            }catch(Exception e){
                response.getWriter().println(e);
            }
            response.getWriter().println();
            response.getWriter().println();
            response.getWriter().println();

            variables.addAll(dc.getVariablesList());
            classes.addAll(dc.getClassList());
            interfaces.addAll(dc.getInterfaceList());
            enums.addAll(dc.getEnumList());
            methods.addAll(dc.getMethodList());

            files.add(new SLFile(s, classes, methods, variables, interfaces, enums, dc.commentCount(cu)));

            /*for(SLClass e: classes){
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
            }*/

            //response.getWriter().println(methods);
            dc.clearAll();
            //response.getWriter().println(methods);

        }


        //todo () add smellls

        for(SLFile file: files){


        }

        /*response.getWriter().println();
        response.getWriter().println("Files empty:" + files.isEmpty());
        response.getWriter().println();*/

        for(SLFile file: files){
            response.getWriter().println("Classes");
            for(SLClass e: file.getClasses()){
                response.getWriter().println("Empty:" + file.getClasses().isEmpty());
                response.getWriter().println(e.toString());
            }
            response.getWriter().println("Methods");
            for(SLMethod e: file.getMethods()){
                response.getWriter().println("Empty:" + file.getMethods().isEmpty());
                response.getWriter().println(e.toString());
            }
            response.getWriter().println("Variables");
            for(SLVariable e: file.getVariables()){
                response.getWriter().println("Empty:" + file.getVariables().isEmpty());
                response.getWriter().println(e.toString());
            }
            response.getWriter().println("Enums");
            for(SLEnum e: file.getEnums()){
                response.getWriter().println("Empty:" + file.getEnums().isEmpty());
                response.getWriter().println(e.toString());
            }
            response.getWriter().println("Interfaces");
            for(SLInterface e: file.getInterfaces()){
                response.getWriter().println("Empty:" + file.getInterfaces().isEmpty());
                response.getWriter().println(e.toString());
            }
            response.getWriter().println("Number of comments:" + file.getCommentCount());

            response.getWriter().println("toString:" + file.toString());
            response.getWriter().println();
            response.getWriter().println();
            response.getWriter().println();
            response.getWriter().println();
        }
        //response.sendRedirect("dashboard.jsp");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}