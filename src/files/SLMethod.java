package files;

import java.util.ArrayList;

public class SLMethod {
    private String scope;
    private String name;
    private String returnType;
    private ArrayList parameters;

    public SLMethod(String name) {
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
