package smells;

import files.SLClass;
import files.SLFile;
import files.SLVariable;

import java.util.ArrayList;

public class PrimitiveObsession {

    private String primitivesRegex = "(byte|short|int|long|float|double|boolean|char)";
    private ArrayList<SLVariable> primitiveVariables = new ArrayList<>();

    public ArrayList<SLVariable> findPrimitiveVariables(ArrayList<SLVariable> variables) {
        for (SLVariable var: variables) {
            if (var.getType().matches(primitivesRegex)) {
                primitiveVariables.add(var);
            }
        }

        return primitiveVariables;
    }

    public boolean isPrimitiveObsession(int numberOfPrimitiveVars, int numberOfVariables) {
        double numPrimitives = numberOfPrimitiveVars;
        double numVariable = numberOfVariables;
        double percentage = numPrimitives/numVariable;

        if (numberOfPrimitiveVars > 9 && percentage > 0.5) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<SLClass> findPrimitiveClasses(ArrayList<SLFile> files) {
        ArrayList<SLClass> primitiveObsessedClasses = new ArrayList<>();

        for (SLFile f: files) {
            for (SLClass c: f.getClasses()) {
                if (isPrimitiveObsession(findPrimitiveVariables(c.getVariables()).size(), c.getVariables().size())) {
                    primitiveObsessedClasses.add(c);
                }
            }
        }

        return primitiveObsessedClasses;
    }
}
