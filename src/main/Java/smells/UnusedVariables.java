package smells;

import files.SLClass;
import files.SLFile;
import files.SLMethod;
import files.SLVariable;

import java.util.HashMap;

public class UnusedVariables {

    public HashMap<SLVariable, Boolean> findVariableUsage(SLFile file, HashMap<SLVariable, Boolean> variableUsage) {
        for (SLClass clazz: file.getClasses()) {
            //get field variables here
            for (SLMethod method:clazz.getMethods()) {
                for (String line : method.getMethodBody()) {
                    for (SLVariable var: variableUsage.keySet()) {
                        variableUsage.put(var, isUsed(line, var.getName()));
                    }
                }
            }
        }

        return variableUsage;
    }

    public boolean isUsed(String line, String variableName) {
        String[] lineSplitUp = line.split("\\s+");
        String lineNoSpaces = line.replaceAll("\\s+", "");

        if (lineNoSpaces.matches(".*([^[a-zA-Z]]|\\s*)"+variableName+"[^[a-zA-Z0-9]].*") &&
                (!isVariableDeclaration(line, variableName) || !variableName.equals(findVariableName(lineSplitUp)))) {
            return true;
        }

        return false;
    }

    private boolean isVariableDeclaration(String line, String variable) {
        return line.matches(".*+"+variable+".*=.*");
    }

    private String findVariableName(String[] lineSplit) {
        String name = "";

        for (int i=0; i<lineSplit.length; i++) {
            if (lineSplit[i].contains("=")) {
                name = lineSplit[i-1];
            } else if (lineSplit[i].contains(";")&&(name.length()<1)) {
                name = lineSplit[i].substring(0,lineSplit[i].length()-1);
            }
        }

        return name;
    }
}
