package files;
import java.util.ArrayList;
import java.util.HashMap;


public class SLClass {

    private String modifier = "";
    private String className="", extendsClassName="";
    private ArrayList<String> interfaces = new ArrayList<>();
    private HashMap<String, Boolean> classBools = new HashMap<>();
    private ArrayList<SLClass> sublclasses = new ArrayList<>();
    private Boolean subclass = false;

    public SLClass(){
        classBools.put("implementsInterfaces",Boolean.FALSE);
        classBools.put("extendsClass",Boolean.FALSE);
        classBools.put("containsInnerClass",Boolean.FALSE);
        classBools.put("final",Boolean.FALSE);
        classBools.put("abstract",Boolean.FALSE);
        classBools.put("strictfp",Boolean.FALSE);
        classBools.put("public",Boolean.FALSE);
        classBools.put("private",Boolean.FALSE);
        classBools.put("protected",Boolean.FALSE);
        classBools.put("static",Boolean.FALSE);
    }

    public ArrayList getSubClasses(){
        return sublclasses;
    }

    public boolean getClassBools(String boolName){
        return classBools.get(boolName);
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

    public void addSubClasses(){
        sublclasses.add(new SLClass());
    }

    public void setClassBoolToTrue(String boolName){
        classBools.put(boolName, true);
    }

    public void setClassModfier(String m){
        modifier = m;
        classBools.put(m, true);
    }



    // get rid of this method in the future, only needed now because the god class uses it
    public void getMethods(){

    }



    public void setClassName(String s){
        if(s.contains("{")){
            className = s.substring(0, s.indexOf("{"));
        }else {
            className = s;
        }
    }

    public void  setExtendsName(String s){
        extendsClassName = s;classBools.put("extendsClass", true);
    }

    public void setImplementsName(String s){
        if(s.contains(",")){
            interfaces.add(s.substring(0, s.indexOf(",")));
        }else {
            interfaces.add(s);
        }
        classBools.put("implementsInterfaces", true);
    }

    public String toString(){
        return   modifier +" "+ className +" "+extendsClassName+
                "\n" + interfaces.toString() + "\n"+ classBools.toString() + "\n" + sublclasses.toString();
    }

    public void setClass(){
        subclass=true;
    }

    public Boolean getSetClass(){
        return subclass;
    }

}