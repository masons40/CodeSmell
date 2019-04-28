package smells;

import files.SLClass;
import files.SLFile;
import files.SLVariable;

import java.util.ArrayList;
import java.util.HashMap;

public class PrimitiveObsession {

    private String primitivesRegex = "(byte|short|int|long|float|double|boolean|char)";
    private HashMap<SLClass, Integer> primitiveObsessionVarNumber = new HashMap<>();

    public PrimitiveObsession(ArrayList<SLFile> files) {
        findPrimitiveClasses(files);
    }

    private ArrayList<SLVariable> findPrimitiveVariables(ArrayList<SLVariable> variables) {
        ArrayList<SLVariable> primitiveVariables = new ArrayList<>();
        for (SLVariable var: variables) {
            if (var.getType().matches(primitivesRegex)) {
                primitiveVariables.add(var);
            }
        }

        return primitiveVariables;
    }

    private boolean isPrimitiveObsession(int numberOfPrimitiveVars, int numberOfVariables) {
        double numPrimitives = numberOfPrimitiveVars;
        double numVariable = numberOfVariables;
        double percentage = numPrimitives/numVariable;

        if (numberOfPrimitiveVars >= 5 && percentage >= 0.5) {
            return true;
        } else {
            return false;
        }

    }

    private ArrayList<SLClass> findPrimitiveClasses(ArrayList<SLFile> files) {
        ArrayList<SLClass> primitiveObsessedClasses = new ArrayList<>();

        for (SLFile f: files) {
            for (SLClass c: f.getClasses()) {
                int numberOfPrimitives = findPrimitiveVariables(c.getVariables()).size();
                if (isPrimitiveObsession(numberOfPrimitives, c.getVariables().size())) {
                    primitiveObsessedClasses.add(c);
                }
                primitiveObsessionVarNumber.put(c, numberOfPrimitives);
            }
        }

        return primitiveObsessedClasses;
    }
}
