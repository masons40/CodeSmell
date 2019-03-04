import java.util.ArrayList;
package SmellTest;

public class Method {
    private String scope;
    private String name;
    private String returnType;
    private ArrayList parameters;

    public Method(String name) {
        this.name = name;
        //this.returnType = returnType;
        //this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    /*public String getReturnType() {
        return returnType;
    }*/

    /*public ArrayList getParameters() {
        return parameters;
    }*/
}
