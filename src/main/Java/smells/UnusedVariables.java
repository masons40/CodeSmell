package smells;

import files.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UnusedVariables {
    private ArrayList<SLFile> files = new ArrayList<>();
    private HashMap<SLVariable, SLClass> unusedVariableClasses = new HashMap<>();
    private ArrayList<SLVariable> unusedVariables = new ArrayList<>();
    private int numberOfUnusedVariables = 0;

    public UnusedVariables(ArrayList<SLFile> files){
        this.files = files;
        findUnusedFieldVariables();
    }

    public ArrayList<SLVariable> findUnusedFieldVariables() {
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

        for (SLVariable variable : unusedVariables) {
            for (SLFile file :files) {
                for (SLClass clazz : file.getClasses()) {
                    if(clazz.getMethods().contains(variable)){
                        unusedVariableClasses.put(variable,clazz);
                    }
                }
            }
        }

        numberOfUnusedVariables = 0;

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
