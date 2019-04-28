package smells;

import files.SLClass;
import files.SLFile;
import files.SLMethod;
import files.SLVariable;

import java.util.ArrayList;

public class LiteralOveruse {

    private String dataTypeRegex = "(byte|short|int|long|float|double|boolean|char|String)";
    private ArrayList<SLVariable> literals = new ArrayList<>();

    public ArrayList<SLVariable> findLiterals(ArrayList<SLFile> files, ArrayList<SLVariable> variables) {

        for (SLFile f: files) {
            for (SLClass c: f.getClasses()) {
                for (SLMethod m: c.getMethods()) {
                    for (String line: m.getMethodBody()) {
                        for (SLVariable var: variables) {
                            isLiteral(line, var);
                        }
                    }
                }
            }
        }

        return literals;
    }

    public boolean isLiteral(String line, SLVariable variable) {
        String variableName = variable.getName();
        if (line.matches("(.*)(byte|short|int|long|float|double)(.*)"+variableName+"(\\s*)=(\\s*)([0-9]+)(.*);(.*)")) {
            return true;
        } else if (line.matches("(.*)(char)(.*)"+variableName+"(\\s*)=(\\s*)('(.*)');(.*)")) {
            return true;
        } else if (line.matches("(.*)(String)(.*)"+variableName+"(\\s*)=(((\\s*)\"(.*)\"(\\s*)(\\+?)(\\s*))+)(.*);(.*)")) {
            return true;
        } else if (line.matches("(.*)(boolean)(.*)"+variableName+"(\\s*)=(\\s*)(true|false)(.*);(.*)")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTooManyLiterals(int numberOfVariables, int numberOfLiterals) {
        double numLiterals = numberOfLiterals;
        double numVariable = numberOfVariables;
        double percentage = numLiterals/numVariable;

        if (numberOfLiterals > 9 || percentage > 0.3) {
            return true;
        } else {
            return false;
        }
   }
}
