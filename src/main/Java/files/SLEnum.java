package files;
import java.util.ArrayList;

public class SLEnum {

    private ArrayList<String> enumVariables = new ArrayList<>();
    private String enumName = "";
    private String modifier = "";
    private Boolean isNested = false;
    private ArrayList<String> nestedEnums = new ArrayList<>();

    public SLEnum(String enumName, String modifier, ArrayList enumVariables, Boolean isNested){
        this.enumName=enumName;
        this.modifier= modifier;
        this.enumVariables=enumVariables;
        this.isNested = isNested;
    }

    public void setName(String s){
        enumName=s;
    }

    public String getName(){
        return enumName;
    }

    public void setModifier(String s){
        modifier=s;
    }

    public String getModifier(){
        return modifier;
    }

    public Boolean getIsNested(){
        return isNested;
    }

    public void addVariable(String s){
        enumVariables.add(s);
    }

    public void addNestedEnum(String s){
        nestedEnums.add(s.replaceAll("\\s+|\\t+", ""));
    }

    public ArrayList<String> getEnumVariables(){
        return enumVariables;
    }

    public  String toString(){
        return modifier+" "+enumName+" "+enumVariables.toString();
    }
}
