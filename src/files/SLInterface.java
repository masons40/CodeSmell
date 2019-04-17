package files;

import java.util.ArrayList;

public class SLInterface {

    ArrayList<SLMethod> interfaceMethods = new ArrayList<>();
    ArrayList<SLVariable> interfaceVariables = new ArrayList<>();
    String interfaceName = "";
    Boolean interfaceExtends = false;
    ArrayList<String> interfaceExtensions = new ArrayList<>();

    public String getName(){
        return interfaceName;
    }
    public ArrayList<SLVariable> getInterfaceVariables(){
        return interfaceVariables;
    }

    public Boolean doesInterfaceExtends(){
        return interfaceExtends;
    }
    public ArrayList<SLMethod> getInterfaceMethods(){
        return interfaceMethods;
    }
    public ArrayList<String> getInterfaceExtensions(){
        return interfaceExtensions;
    }
}
