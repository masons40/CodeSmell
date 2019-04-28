package files;
import java.util.ArrayList;
import java.util.Arrays;

/*
Class used to represent a method
 */
public class SLMethod {
    private String accessor;
    private String name;
    private String returnType;
    private ArrayList<String> methodBody;
    private ArrayList<String> parameters;
    private ArrayList<SLVariable> methodVariables;

    public SLMethod(String name, String accessor, String returnType, ArrayList parameters, String methodbody, ArrayList methodVariables) {
        this.name = name;
        this.accessor = accessor;
        this.returnType = returnType;
        this.parameters = parameters;
        this.methodBody = new ArrayList<>(Arrays.asList(methodbody.split("\n")));
        this.methodVariables = methodVariables;
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

    public ArrayList<String> getMethodBody(){
        return methodBody;
    }

    public ArrayList getVariables(){
        return methodVariables;
    }

    public String methVariables(){
        String res ="";
        for(SLVariable v: methodVariables){
            res+="Variable found:" + v.toString()+"\n";
        }
        return res;
    }

    public String methBody(){
        String res ="";
        for(String v: methodBody){
            res+=v+"\n";
        }
        res+="\n";
        return res;
    }

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