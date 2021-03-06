package smells;

import files.*;

import java.util.ArrayList;
import java.util.HashMap;


/*
Unused methods returns information about all unused methods such as what class they are in,
how many exist and how many exist per file
 */
public class UnusedMethods {
    private transient ArrayList<SLFile> files = new ArrayList<>();
    private transient HashMap<SLMethod, Integer> methodUsage = new HashMap<>();
    private HashMap<String, String> unusedMethodsClasses = new HashMap<>();
    private transient ArrayList<SLMethod> unusedMethods = new ArrayList<>();
    private HashMap<String, Integer> unusedMethodsPerClass = new HashMap<>();
    private int numberOfUnusedMethods = 0;
    private boolean smellPresent = false;

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

        //hashmap of method to the class the method is in is initialized here
        for (SLFile file :files) {
            for (SLClass clazz : file.getClasses()) {
                for (SLMethod method : unusedMethods) {
                    for (SLMethod classMethod:clazz.getMethods()) {
                        if(classMethod.getName().equals(method.getName())){
                            unusedMethodsClasses.put(method.getName(),clazz.getClassName());
                        }
                    }
                }
            }
        }

        //a hash map of class to the number of unused methods per class is initialized here
        for (SLMethod method:unusedMethods) {
            String clazz = unusedMethodsClasses.get(method.getName());
            if(unusedMethodsPerClass.containsKey(clazz)) {
                unusedMethodsPerClass.put(clazz, unusedMethodsPerClass.get(clazz)+1);
            }
            else {
                unusedMethodsPerClass.put(clazz,1);
            }
        }


        numberOfUnusedMethods = unusedMethods.size();

        if (unusedMethods.size()>0) {
            smellPresent = true;
        }

        return unusedMethods;
    }

}

