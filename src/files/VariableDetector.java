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

        return name;
    }

    public String findValue(String[] lineSplit) {
        String value = "";

        return value;
    }
}
