package files;
import java.util.ArrayList;
import java.util.HashMap;


public class SLClass {

    private String modifier = "";
    private String className="", extendsClassName="";
    private ArrayList<String> interfaces = new ArrayList<>();
    private ArrayList<String> sublclasses = new ArrayList<>();
    private ArrayList<SLMethod> methods = new ArrayList<>();
    private ArrayList<SLVariable> variables = new ArrayList<>();
    private Boolean subclass;

    public SLClass(ArrayList<String> modifier, String className, ArrayList<String> extendsClassName, ArrayList interfaces, Boolean sublcass, ArrayList methods, ArrayList variables){
        if(!(modifier.isEmpty())){
            this.modifier = modifier.get(0);
        }else{
            this.modifier = "";
        }
        this.className = className;
        if(!(extendsClassName.isEmpty())){
            this.extendsClassName=extendsClassName.get(0);
        }else{
            this.extendsClassName="";
        }
        this.interfaces = interfaces;
        this.subclass = sublcass;
        this.methods = methods;
        this.variables = variables;

    }

    public ArrayList getSubClasses(){
        return sublclasses;
    }

    public String getClassModfier(){
        return modifier;
    }

    public String getClassName(){
        return className;
    }

    public String getExtendsName(){
        return extendsClassName;
    }

    public ArrayList getInterfaceNames(){
        return interfaces;
    }

    public ArrayList<SLMethod> getMethods(){
        return methods;
    }

    public ArrayList<SLVariable> getVariables(){
        return variables;
    }


    public void addSubClasses(String s){
        sublclasses.add(s.replaceAll("\\s+|\\t+", ""));
    }



    public void setImplementsName(String s){
        interfaces.add(s.replaceAll("\\s+|\\t+","" ));
        //classBools.put("implementsInterfaces", true);
    }

    public String toString(){
        return modifier +" "+ className +" "+extendsClassName+
                "\n" + interfaces.toString() + "\n" + sublclasses.toString();
    }
    public String variablesString(){
        String res="";
        for(SLVariable v: variables){
            res+=v.toString()+"\n";
        }
        return res;
    }

    public String methodString(){
        String res="";
        for(SLMethod v: methods){
            res+=v.toString()+"\n";
        }
        return res;
    }


    public Boolean getSubClass(){
        return subclass;
    }

}