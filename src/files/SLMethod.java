package files;
import java.util.ArrayList;

public class SLMethod {
    private String accessor;
    private String name;
    private String returnType;
    private ArrayList<String> parameters;

    public SLMethod(String name, String accessor, String returnType, ArrayList parameters) {
        this.name = name;
        this.accessor = accessor;
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public String getAccessor(){
        return accessor;
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public ArrayList getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        String result = accessor + " " + returnType + " " + name + "(" ;
        for (int i = 0; i < parameters.size(); i++) {
            if(i<parameters.size()-1) {
                result += parameters.get(i) + ",";
            }
            else {
                result += parameters.get(i);
            }
        }
        result += ")";
        return result;
    }
}
