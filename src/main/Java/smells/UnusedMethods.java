package smells;

import files.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UnusedMethods {
    private transient ArrayList<SLFile> files = new ArrayList<>();
    private HashMap<SLMethod, Integer> methodUsage = new HashMap<>();
    private HashMap<SLMethod, SLClass> unusedMethodsClasses = new HashMap<>();
    private ArrayList<SLMethod> unusedMethods = new ArrayList<>();
    private int numberOfUnusedMethods = 0;

    public UnusedMethods(ArrayList<SLFile> files){
        this.files = files;
        findUnusedMethods();
    }

    //Method returns an array  list of methods that have not been used in the
    public ArrayList<SLMethod> findUnusedMethods(){
        ArrayList<String> fileCode = new ArrayList<>();

        for (SLFile slFile:files) {
            for(SLMethod method : slFile.getMethods()) {
                methodUsage.put(method,0);
                fileCode.addAll(method.getMethodBody());
            }
        }

        //for each line of code we need to check if a method is called in the line
        for (String line : fileCode) {
            for (SLMethod method : methodUsage.keySet()) {
                if(line.contains(method.getName() + "(")){
                    int count = methodUsage.get(method);
                    count++;
                    methodUsage.put(method, count);
                }
            }
        }

        //add all unused methods to an arrayList
        for (SLMethod method : methodUsage.keySet()) {
            if(methodUsage.get(method)==0){
                unusedMethods.add(method);
            }
        }

        for (SLMethod method : unusedMethods) {
            for (SLFile file :files) {
                for (SLClass clazz : file.getClasses()) {
                    if(clazz.getMethods().contains(method)){
                        unusedMethodsClasses.put(method,clazz);
                    }
                }
            }
        }

        numberOfUnusedMethods = unusedMethods.size();

        return unusedMethods;
    }

}

