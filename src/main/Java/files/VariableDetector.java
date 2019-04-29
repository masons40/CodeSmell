package files;

import java.util.ArrayList;

/**
 * Class that can detect variables
 */

public class VariableDetector {

    private String dataTypeRegex = "(byte|short|int|long|float|double|boolean|char|String)";
    private String accessorsRegex = "(public|private|protected|static|final|native|synchronized|abstract|transient)";
    private ArrayList<SLVariable> variableList = new ArrayList<>();

    /**
     * checks the lines of the files to see if there are variable declarations
     * @param line line of code
     * @return an array of variables
     */

    public ArrayList<SLVariable> checkForVariables(String line) {
        String[] lineSplit =line.split("\\s+");

        if (isVariable(line)){
            String variableName = findName(lineSplit);
            String variableType = findType(lineSplit, variableName);
            String variableScope = findScope(lineSplit);

            SLVariable var = new SLVariable(variableScope, variableType, variableName);
            variableList.add(var);
        }

        return variableList;
    }

    /**
     * checks a line of code to see if there has been a variable dec;aration on that line
     * @param line line of code
     * @return whether that line has a variable declaration or not
     */

    public boolean isVariable(String line) {
        line = line.replaceAll("\\s+", "");

        if (line.matches(dataTypeRegex+".*")) {
            if (line.contains("=")||line.contains(";")) {
                return true;
            } else {
                return false; //is a poorly declared method without the scope defined
            }
        } else if (line.matches(".*=new.*")) {
            return true;
        } else if (line.matches(("(.*)(\\[(.*)])(.*)=(.*)"))) {
            return true;
        } else if (line.matches(accessorsRegex+".*")) {
            if (line.contains("=") || line.contains(";")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Used to find a variable's scope
     * @param lineSplit array split on spaces
     * @return string of the scope specified if any
     */

    private String findScope(String[] lineSplit) {
        String scope = "";

        for(int i=0; i < lineSplit.length; i++) {
            if (lineSplit[i].matches(accessorsRegex)) {
                scope += " " + lineSplit[i];
            }
        }

        return scope;
    }

    /**
     * Used to find a variable's type
     * @param lineSplit array split on spaces
     * @param variableName name of the variable
     * @return string with the variable's type
     */

    private String findType(String[] lineSplit, String variableName) {
        String type = "";

        for (int i=0; i<lineSplit.length; i++) {
            if (lineSplit[i].matches(variableName+"(.*)")) {
                if (lineSplit[i-1].endsWith(">") && !lineSplit[i-1].contains("<")) {
                    type = lineSplit[i-2]+" "+lineSplit[i-1];
                } else {
                    type = lineSplit[i - 1];
                }
            }
        }

        return type;
    }

    /**
     * method to find a variable's declared name
     * @param lineSplit array split on spaces
     * @return string with the variable's name
     */

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

    /**
     * get the list of variables
     * @return an ArrayList of variables
     */

    public ArrayList<SLVariable> getVariableList(){
        return variableList;
    }


}
