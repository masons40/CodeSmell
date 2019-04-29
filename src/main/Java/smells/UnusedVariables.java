package smells;

import files.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class checks if there are variables in the project that are declared but never used
 */
public class UnusedVariables {
    private transient ArrayList<SLFile> files = new ArrayList<>();
    private HashMap<String, String> unusedVariableClasses = new HashMap<>();
    private transient ArrayList<SLVariable> unusedVariables = new ArrayList<>();
    private HashMap<String, Integer> unusedVariablesPerClass = new HashMap<>();
    private int numberOfUnusedVariables = 0;
    private boolean smellPresent = false;

    public UnusedVariables(ArrayList<SLFile> files){
        this.files = files;
        findUnusedFieldVariables();
    }

    /**
     * This method searches through the ArrayList of files.
     * From there it will go through every class, get its variables and
     * then go through the methods to see if those variables are ever true
     * @return It returns an ArrayList of these variables
     */

    public ArrayList<SLVariable> findUnusedFieldVariables() {
        ArrayList<SLVariable> remove = new ArrayList<>();

        for (SLFile f: files) {
            for (SLClass c: f.getClasses()) {
                ArrayList<SLVariable> classVars = c.getVariables();
                for (SLMethod m: c.getMethods()) {
                    for (String line: m.getMethodBody()) {
                        for (SLVariable var: classVars) {
                            if (isUsed(line, var.getName())) {
                                remove.add(var);
                            }
                        }
                    }
                }
                classVars.removeAll(remove);
                unusedVariables.addAll(classVars);
            }
        }

        for (SLVariable variable : unusedVariables) {
            for (SLFile file :files) {
                for (SLClass clazz : file.getClasses()) {
                    for (SLVariable classVariable:clazz.getVariables()) {
                        if (classVariable.getName().equals(variable.getName())) {
                            unusedVariableClasses.put(variable.getName(), clazz.getClassName());
                        }
                    }
                }
            }
        }

        for (SLVariable var: unusedVariables) {
            String clazz = unusedVariableClasses.get(var.getName());
            if (unusedVariablesPerClass.containsKey(clazz)) {
                unusedVariablesPerClass.put(clazz, unusedVariablesPerClass.get(clazz)+1);
            } else {
                unusedVariablesPerClass.put(clazz, 1);
            }
        }

        numberOfUnusedVariables = unusedVariables.size();

        if (unusedVariables.size()>0) {
            smellPresent = true;
        }

        return unusedVariables;
    }

    /**
     * Checks if a variable was used on a certain line of code
     * @param line a line of code
     * @param variableName the name of the variable we are checking
     * @return true or false whether the variable is used
     */

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

    /**
     * Method to find a variable's name
     * @param lineSplit the line split up as an array on every space
     * @return a string of the name of the variable
     */

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
