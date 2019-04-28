package smells;

import files.SLClass;
import files.SLFile;
import files.SLVariable;

import java.util.ArrayList;
import java.util.HashMap;

public class PrimitiveObsession {

    private transient String primitivesRegex = "(byte|short|int|long|float|double|boolean|char)";
    private HashMap<String, Integer> primitiveObsessionVarNumber = new HashMap<>();
    private ArrayList<String> primitiveObsessedClasses = new ArrayList<>();

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

        if (numberOfPrimitiveVars > 9 && percentage > 0.5) {
            return true;
        } else {
            return false;
        }

    }

    private void findPrimitiveClasses(ArrayList<SLFile> files) {

        for (SLFile f: files) {
            for (SLClass c: f.getClasses()) {
                int numberOfPrimitives = findPrimitiveVariables(c.getVariables()).size();
                if (isPrimitiveObsession(numberOfPrimitives, c.getVariables().size())) {
                    primitiveObsessedClasses.add(c.getClassName());
                }
                primitiveObsessionVarNumber.put(c.getClassName(), numberOfPrimitives);
            }
        }
    }
}
