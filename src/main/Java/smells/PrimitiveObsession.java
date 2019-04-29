package smells;

import files.SLClass;
import files.SLFile;
import files.SLVariable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that checks whether or not primitive obsession is present in a class
 */

public class PrimitiveObsession {

    private transient String primitivesRegex = "(byte|short|int|long|float|double|boolean|char)";
    private HashMap<String, Integer> primitiveObsessionVarNumber = new HashMap<>();
    private ArrayList<String> primitiveObsessedClasses = new ArrayList<>();
    private boolean smellPresent = false;

    public PrimitiveObsession(ArrayList<SLFile> files) {
        findPrimitiveClasses(files);
    }

    /**
     * Goes through a list of variables and returns a list of those that are primitive
     * @param variables array of variable objects
     * @return the ArrayList of primitive variables
     */

    private ArrayList<SLVariable> findPrimitiveVariables(ArrayList<SLVariable> variables) {
        ArrayList<SLVariable> primitiveVariables = new ArrayList<>();
        for (SLVariable var: variables) {
            if (var.getType().matches(primitivesRegex)) {
                primitiveVariables.add(var);
            }
        }

        return primitiveVariables;
    }

    /**
     * A metric to see if a class suffers from primitive obsession
     * @param numberOfPrimitiveVars number of primitives
     * @param numberOfVariables number of variables
     * @return a boolean as to whether the class is primitively obsessed
     */

    private boolean isPrimitiveObsession(int numberOfPrimitiveVars, int numberOfVariables) {
        double numPrimitives = numberOfPrimitiveVars;
        double numVariable = numberOfVariables;
        double percentage = numPrimitives/numVariable;

        if (numberOfPrimitiveVars >= 5 && percentage >= 0.5) {
            smellPresent = true;
            return true;
        } else {
            return false;
        }

    }

    /**
     * Goes through amd finds the classes that are primitively obsessed
     * @param files array of files
     */

    private void findPrimitiveClasses(ArrayList<SLFile> files) {

        for (SLFile f: files) {
            for (SLClass c: f.getClasses()) {
                int numberOfPrimitives = findPrimitiveVariables(c.getVariables()).size();
                if (isPrimitiveObsession(numberOfPrimitives, c.getVariables().size())) {
                    primitiveObsessedClasses.add(c.getClassName());
                }
                if (numberOfPrimitives > 0) {
                    primitiveObsessionVarNumber.put(c.getClassName(), numberOfPrimitives);
                }
            }
        }
    }
}
