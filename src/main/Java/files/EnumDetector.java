package files;
import java.util.ArrayList;

public class EnumDetector {

    private int enumCounter = 0;
    private ArrayList<SLEnum> enums = new ArrayList<>();
    private ArrayList<String> modifiers = new ArrayList<>();
    private boolean modifierSet=false;

    public EnumDetector(){
        modifiers.add("final");
        modifiers.add("abstract");
        modifiers.add("strictfp");
        modifiers.add("private");
        modifiers.add("protected");
        modifiers.add("static");
        modifiers.add("public");
    }

    public String enumCreator(String line){
        //enums.add(new SLEnum());
        String[] enumLine = line.split(" ");
        if(line.contains("{")){

            line=line.substring(line.indexOf("{"));
            variableCleanUp(line);
        }

        for(int i=0;i<enumLine.length;i++){
            enumLine[i] = enumLine[i].replaceAll("\\s+","");
            enumLine[i] = enumLine[i].replaceAll("\\t+","");
            if(modifiers.contains(enumLine[i])){
                enums.get(enumCounter).setModifier(enumLine[i]);
                //System.out.println("Modifier:" + enumLine[i]);
                modifierSet=true;
            }
        }


        if(modifierSet){
            enums.get(enumCounter).setName(enumLine[2]);
            //System.out.println("Name:" + enumLine[2]);
        }else{
            enums.get(enumCounter).setName(enumLine[1]);
            //System.out.println("Name:" + enumLine[1]);
        }
        return enums.get(enumCounter).toString();
    }

    public void variableCleanUp(String line){
        String[] variableList = line.split(",");

        for(int i=0;i<variableList.length;i++){
            String variable = variableList[i].replaceAll("\\s+","");
            variable=variable.replaceAll("\\t+","");
            variable=variable.replaceAll(",+","");
            variable=variable.replaceAll("}","");
            variable=variable.replaceAll("\\{","");
            System.out.println("variable: "+ variable);
            enums.get(enumCounter).addVariable(variable);
        }
    }

    public String toString(){
        return enums.get(enumCounter).toString();
    }
}
