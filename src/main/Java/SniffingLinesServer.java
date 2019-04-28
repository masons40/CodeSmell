import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import files.*;
import management.FileTransfer;
import smells.GeneralOverview;
import smells.GodClasses;
import smells.PrimitiveObsession;
import smells.UnusedVariables;

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
            variables.addAll(dc.getVariablesList());
            classes.addAll(dc.getClassList());
            interfaces.addAll(dc.getInterfaceList());
            enums.addAll(dc.getEnumList());
            methods.addAll(dc.getMethodList());
            files.add(new SLFile(s, classes, methods, variables, interfaces, enums, dc.commentCount(cu)));
            dc.clearAll();
        }
        for(SLFile file: files){
//            for(SLClass e: file.getClasses()){
//                response.getWriter().println("Class Methods");
//                for(SLMethod m: e.getMethods()){
//                    response.getWriter().println(m.toString());
//                    response.getWriter().println("Does the method contains variables(size):" + m.getVariables().size());
//                    response.getWriter().println(m.methBody());
//                }
//            }
//            response.getWriter().println("Methods");
//            for(SLMethod e: file.getMethods()){
//                response.getWriter().println(e.toString());
//                response.getWriter().println(e.methBody());
//                response.getWriter().println(e.methVariables());
//            }
//            response.getWriter().println("Variables");
//            for(SLVariable e: file.getVariables()){
//                response.getWriter().println(e.toString());
//            }
//            response.getWriter().println("Enums");
//            for(SLEnum e: file.getEnums()){
//                response.getWriter().println(e.toString());
//            }
//            response.getWriter().println("Interfaces");
//            for(SLInterface e: file.getInterfaces()){
//                response.getWriter().println(e.toString());
//            }
//            response.getWriter().println("Number of comments:" + file.getCommentCount());
        }
        GeneralOverview go = new GeneralOverview(files, "tester", javaFileNames);
        GodClasses gc = new GodClasses(files);
        Gson gson = new Gson();
        PrimitiveObsession primitiveObsession = new PrimitiveObsession(files);
        //response.getWriter().println(primitiveObsession);

//        for (SLFile f: files) {
//            response.getWriter().println(f.getName());
//            for (SLMethod m: f.getMethods()) {
//                response.getWriter().println(m.getName());
//                response.getWriter().println(m.findMethodVariables());
//            }
//        }

//        UnusedVariables unusedVariables = new UnusedVariables();
//        response.getWriter().println(unusedVariables.findUnusedFieldVariables(files));

//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        request.setAttribute("jsonData",gson.toJson(primitiveObsession));
//        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}