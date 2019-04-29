package smells;

import files.SLClass;
import files.SLFile;
import files.SLMethod;

import java.util.ArrayList;

public class BloatedMethods {
    private ArrayList<SLFile> files = new ArrayList<>();
    private int numberOfBloatedMethods = 0;
    private boolean smellPresent = false;

    public BloatedMethods(ArrayList<SLFile> files){
        this.files=files;
    }

    public ArrayList<SLMethod> findBloatedMethods(){
        ArrayList<SLMethod> bloatedMethods = new ArrayList<>();
        for (SLFile slFile : files) {
            for (SLClass clazz : slFile.getClasses()) {
                for (SLMethod method: clazz.getMethods()) {
                    if (method.getMethodBody().size()>150) {
                        bloatedMethods.add(method);
                    }
                }
            }
        }

        numberOfBloatedMethods = bloatedMethods.size();

        if (bloatedMethods.size()>0) {
            smellPresent = true;
        }

        return bloatedMethods;
    }

}
