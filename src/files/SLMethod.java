package files;

import java.util.ArrayList;

public class SLMethod {
    private String accessor;
    private String name;
    private String returnType;
    private ArrayList parameters;

    public SLMethod(String name, String accessor) {
        this.name = name;
        this.accessor = accessor;
        //this.returnType = returnType;
        //this.parameters = parameters;
    }

    public String getAccessor(){
        return accessor;
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
