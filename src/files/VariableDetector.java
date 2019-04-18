package files;

import java.util.ArrayList;
import java.util.HashMap;

public class VariableDetector {

    private String dataTypeRegex = "(byte|short|int|long|float|double|boolean|char|String)";
    private String accessorsRegex = "(public|private|protected|static|final|native|synchronized|abstract|transient)";
    HashMap<String, Boolean> variableMap = new HashMap<>();

    private ArrayList<SLVariable> variableList = new ArrayList<>();

    public void checkForVariables(String line) {
        String[] lineSplit =line.split("\\s+");

        if (isVariable(line)){
            variableList.add(new SLVariable(findScope(lineSplit), findType(lineSplit),findName(lineSplit),findValue(lineSplit)));
            variableMap.put(findName(lineSplit), false);
        }

        isUsed(line);
    }

    private boolean isVariable(String line) {

        line = line.replaceAll("\\s+", "");

        if (line.matches(dataTypeRegex+".*")) {
            if (line.contains("=")||line.contains(";")) {
                return true;
            } else {
                return false; //is a poorly declared method without the scope defined
            }

        } else if (line.matches(".*=new.*")) {
            return true;
        } else {
            return false;
        }
    }

    private String findScope(String[] lineSplit) {
        String scope = "";

        for(int i=0; i < lineSplit.length; i++) {
            if (lineSplit[i].matches(accessorsRegex)) {
                scope += " " + lineSplit[i];
            }
        }

        return scope;
    }

    private String findType(String[] lineSplit) {
        String type = "";

        for (int i=0; i<lineSplit.length; i++) {
            if (lineSplit[i].matches(dataTypeRegex)) {
                type = lineSplit[i];
            }
        }

        return type;
    }

    private String findName(String[] lineSplit) {
        String name = "";

        for (int i=0; i<lineSplit.length; i++) {
            if (lineSplit[i].contains("=")) {
                //variable declaration and assigning a value too using = sign
                if (lineSplit[i].matches("[a-zA-Z0-9_]+(=)")) {
                    name = lineSplit[i].substring(0,lineSplit[i].length()-1);
                } else if (lineSplit[i].matches("(=).*")) {
                    name = lineSplit[i-1];
                } else if (lineSplit[i].matches("[a-zA-Z0-9_]+(=).*")) {
                    String[] noSpacesBetweenEqual = lineSplit[i].split("=");
                    name = noSpacesBetweenEqual[0];
                }
            } else if (lineSplit[i].contains(";")&&(name.length()<1)) {
                if (lineSplit[i].matches("[a-zA-Z0-9_]+(;)")) {
                    //variable declaration without assigning a value to it
                    name = lineSplit[i].substring(0,lineSplit[i].length()-1);
                } else {
                    //space between semicolon and variable name (bad)
                    String[] noSpacesBetweenSemiColon = lineSplit[i-1].split("\\s+");
                    name = noSpacesBetweenSemiColon[0];
                }
            }
        }

        return name;
    }

    private String findValue(String[] lineSplit) {
        String value = "";
        return value;
    }

    public boolean isUsed(String lineSplit) {
        String[] lineSplitUp = lineSplit.split("\\s+");
        String lineNoSpaces = lineSplit.replaceAll("\\s+", "");

        for (String key: variableMap.keySet()) {
            if (lineNoSpaces.matches(".*([^[a-zA-Z]]|\\s*)"+key+"[^[a-zA-Z0-9]].*") &&
                    (!isVariable(lineSplit) || !key.equals(findName(lineSplitUp)))) {
                variableMap.put(key, true);
            }
        }

        return false;
    }

    public ArrayList<SLVariable> getVariableList(){
        return variableList;
    }
}
