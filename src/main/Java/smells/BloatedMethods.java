package smells;

import files.SLClass;
import files.SLFile;
import files.SLMethod;

import java.util.ArrayList;

public class BloatedMethods {
    private ArrayList<SLFile> files = new ArrayList<>();
    private int numberOfBloatedMethods = 0;

    public BloatedMethods(ArrayList<SLFile> files){
        this.files=files;
    }

    public ArrayList<SLMethod> findBloatedMethods(){
        ArrayList<SLMethod> bloatedMethods = new ArrayList<>();
        for (SLFile slFile : files) {
            for (SLClass clazz : slFile.getClasses()) {
                for (SLMethod method: clazz.getMethods()) {
                    if (method.getMethodBody().size()>200) {
                        bloatedMethods.add(method);
                    }
                }
            }
        }

        numberOfBloatedMethods = bloatedMethods.size();
        return bloatedMethods;
    }

}
