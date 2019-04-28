package smells;

import files.SLClass;
import files.SLFile;
import files.SLMethod;
import files.SLVariable;

import java.util.ArrayList;

public class LiteralOveruse {

    private String dataTypeRegex = "(byte|short|int|long|float|double|boolean|char|String)";
    private ArrayList<SLVariable> literals = new ArrayList<>();

    public ArrayList<SLVariable> findLiterals(SLFile file, ArrayList<SLVariable> variables) {

        for (SLClass clazz: file.getClasses()) {
            for (SLMethod method:clazz.getMethods()) {
                for (String line : method.getMethodBody()) {
                    for (SLVariable var: variables) {
                        isLiteral(line, var.getName());
                    }
                }
            }
        }

        return literals;
    }

    public boolean isLiteral(String line, String variableName) {
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

        if (numberOfLiterals > 9 && percentage > 0.3) {
            return true;
        } else {
            return false;
        }
   }
}
