package smells;

import files.*;

import java.util.ArrayList;

public class UnusedVariables {

    public ArrayList<SLVariable> findUnusedFieldVariables(ArrayList<SLFile> files) {
        ArrayList<SLVariable> unusedVariables = new ArrayList<>();

        ArrayList<SLVariable> remove = new ArrayList<>();

        for (SLFile f: files) {
            for (SLClass c: f.getClasses()) {
                unusedVariables.addAll(c.getVariables());
                for (SLMethod m: c.getMethods()) {
                    unusedVariables.addAll(m.findMethodVariables());
                    for (String line: m.getMethodBody()) {
                        for (SLVariable var: unusedVariables) {
                            if (isUsed(line, var.getName())) {
                                remove.add(var);
                            }
                        }
                    }
                }
            }
        }

        unusedVariables.removeAll(remove);

        return unusedVariables;

    }

    public boolean isUsed(String line, String variableName) {
        String[] lineSplitUp = line.split("\\s+");
        String lineNoSpaces = line.replaceAll("\\s+", "");

        VariableDetector detector = new VariableDetector();

        if (lineNoSpaces.matches(".*([^[a-zA-Z]]|\\s*)"+variableName+"[^[a-zA-Z0-9]].*") &&
                (!detector.isVariable(line) || !variableName.equals(findVariableName(lineSplitUp)))) {
            return true;
        }

        return false;
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
