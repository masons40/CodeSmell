package files;

import java.util.ArrayList;

public class SLInterface {

    private ArrayList<SLMethod> interfaceMethods;
    private ArrayList<SLVariable> interfaceVariables;
    private String interfaceName = "";
    private ArrayList<String> interfaceExtends;


    public SLInterface(ArrayList interfaceMethods, ArrayList interfaceVariables, String interfaceName, ArrayList interfaceExtends){

        this.interfaceExtends=interfaceExtends;
        this.interfaceMethods=interfaceMethods;
        this.interfaceName=interfaceName;
        this.interfaceVariables=interfaceVariables;
    }

    public String getName(){
        return interfaceName;
    }
    public ArrayList<SLVariable> getInterfaceVariables(){
        return interfaceVariables;
    }

    public ArrayList<String> interfaceExtends(){
        return interfaceExtends;
    }
    public ArrayList<SLMethod> getInterfaceMethods(){
        return interfaceMethods;
    }


    public String toString(){
        String s = "";
        s+=interfaceName + " ";
        for(SLVariable v: interfaceVariables){
            s+= v.toString()+" ";
        }
        for(SLMethod m: interfaceMethods){
            s+= m.toString()+" ";
        }
        for(String i: interfaceExtends){
            s+= i+" ";
        }
        return s;
    }

}
