package smells;

import files.SLClass;
import files.SLFile;
import files.SLMethod;

import java.util.ArrayList;

public class BloatedMethods {
    private transient ArrayList<SLFile> files = new ArrayList<>();
    private int numberOfBloatedMethods = 0;
    private boolean smellPresent = false;

    private ArrayList<String> bloatedMethods = new ArrayList<>();

    public BloatedMethods(ArrayList<SLFile> files){
        this.files=files;
        findBloatedMethods();
    }

    public void findBloatedMethods(){
        for (SLFile slFile : files) {
            for (SLClass clazz : slFile.getClasses()) {
                for (SLMethod method: clazz.getMethods()) {
                    if (method.getMethodBody().size()>150) {
                        bloatedMethods.add(method.getName());
                    }
                }
            }
        }

        numberOfBloatedMethods = bloatedMethods.size();

        if (bloatedMethods.size()>0) {
            smellPresent = true;
        }

    }

}
