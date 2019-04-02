package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class VariableDetector {

    String dataTypeRegex = String.format("(byte|short|int|long|float|double|boolean|char|String)");
    String accessorsRegex = String.format("(public|private|protected|static|final|native|synchronized|abstract|transient)");

    public void checkForVariables(String directoryAddress) throws Exception {
        File dir = new File(directoryAddress);

        for (File fileDir : dir.listFiles()) {
            ArrayList<SLVariable> variables = new ArrayList<>();

            if(fileDir.getName().substring(fileDir.getName().indexOf(".") + 1).equals("java")) {
                BufferedReader br = new BufferedReader(new FileReader(fileDir));

                String line;

                while ((line = br.readLine()) != null) {
                    String variableScope = "";
                    String variableType = "";
                    String variableName = "";
                    String variableValue = "";
                    String[] lineSplit =line.split("\\s+");

                    if (isVariable(line)){
                        variableScope = findScope(lineSplit);
                        variableType = findType(lineSplit);
                        variableName = findName(lineSplit);
                        variableValue = findValue(lineSplit);
                    }

                }
            }

        }
    }

    public boolean isVariable(String line) {

        line = line.replaceAll("\\s+", "");

        if (line.matches(dataTypeRegex+".*")) {
            if (line.contains("=")||line.contains(";")) {
                return true;
            } else {
                return false; //is a poorly declared method without the scope defined (smelly!!!)
            }

        } else if (line.matches(".*=new.*")) {
            return true;
        } else {
            return false;
        }
    }

    public String findScope(String[] lineSplit) {
        String scope = "";

        for(int i=0; i < lineSplit.length; i++) {
            if (lineSplit[i].matches(accessorsRegex)) {
                scope += " " + lineSplit[i];
            }
        }

        return scope;
    }

    public String findType(String[] lineSplit) {
        String type = "";

        for (int i=0; i<lineSplit.length; i++) {
            if (lineSplit[i].matches(dataTypeRegex)) {
                type = lineSplit[i];
            }
        }

        return type;
    }

    public String findName(String[] lineSplit) {
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

    public String findValue(String[] lineSplit) {
        String value = "";

        return value;
    }
}
