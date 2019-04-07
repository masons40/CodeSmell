package files;

import java.util.ArrayList;

public class SLClass {

    String name = "";
    ArrayList<SLMethod> methods = new ArrayList<>();
    ArrayList<SLVariable> variables = new ArrayList<>();
    ArrayList<SLClass> subClasses = new ArrayList<>();
    SLClass parentClass = null;

    public SLClass(String name, ArrayList<SLMethod> methods, ArrayList<SLVariable> variables, ArrayList<SLClass> subClasses, SLClass parentClass){
        this.name = name;
        this.methods = methods;
        this.variables = variables;
        this.subClasses = subClasses;
        this.parentClass = parentClass;
    }

    public String getName() {
        return name;
    }

    public ArrayList<SLMethod> getMethods() {
        return methods;
    }

    public ArrayList<SLVariable> getVariables() {
        return variables;
    }

    public ArrayList<SLClass> getSubClasses() {
        return subClasses;
    }

    public SLClass getParentClass() {
        return parentClass;
    }

    public boolean hasParentClass(){
        return parentClass != null;
    }

}
