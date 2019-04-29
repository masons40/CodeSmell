import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.google.gson.*;
import files.*;
import management.FileTransfer;
import smells.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@MultipartConfig
@WebServlet("/SniffingLinesServer")
public class SniffingLinesServer extends HttpServlet {
    private FileTransfer fileTransfer;
    private ArrayList<String> javaFileNames;
    private String javaFilePath="";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        fileTransfer = new FileTransfer(request, response);
        javaFileNames = fileTransfer.getfileNames();
        javaFilePath = fileTransfer.getJavaFilePath();
        DataCollection dc = new DataCollection();
        String companyName = fileTransfer.getCompanyName();

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

        GeneralOverview go = new GeneralOverview(files, companyName, javaFileNames);
        GodClasses gc = new GodClasses(files);
        PrimitiveObsession primitiveObsession = new PrimitiveObsession(files);
        UnusedMethods um = new UnusedMethods(files);
        UnusedVariables uv = new UnusedVariables(files);
        BloatedMethods bm = new BloatedMethods(files);
        ArrowHead ah = new ArrowHead(files);

        /*
        creating a hashmap of objects allows us to easily create the json we use to display all of the data on teh dashboard
         */
        HashMap<String, Object> objects = new HashMap<>();
        objects.put("GeneralOverview", go);
        objects.put("GodClass", gc);
        objects.put("PrimitiveObsession", primitiveObsession);
        objects.put("UnusedMethods", um);
        objects.put("UnusedVariables", uv);
        objects.put("BloatedMethods", bm);
        objects.put("ArrowHead", ah);

        Gson gson = new Gson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("jsonData",gson.toJson(objects));
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}