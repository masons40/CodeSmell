package files;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MethodVisitor extends VoidVisitorAdapter {

    private HttpServletResponse response;
    public MethodVisitor(HttpServletResponse response){
        this.response = response;
    }
    public void visit(MethodDeclaration n, Object arg)
    {
        try {
            response.getWriter().println(n.isMethodDeclaration());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
