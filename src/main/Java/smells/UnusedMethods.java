package smells;

import files.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class UnusedMethods {

    //Method takes in a hash map of methods to integer where integer value is the number of times a method has been used
    public HashMap<SLMethod, Integer> findMethodUsage(SLClass clazz, HashMap<SLMethod, Integer> methodUsage){
        //loop through each line of code in the class and check if those lines use a method
        for (SLMethod searchClassMethod : clazz.getMethods()) {
            for (String line : searchClassMethod.getMethodBody()) {
                for (SLMethod method : methodUsage.keySet()) {
                    if (line.contains(method.getName() + "(")) {
                        int count = methodUsage.get(method);
                        count++;
                        methodUsage.put(method, count);
                    }
                }
            }
        }
        return methodUsage;
    }

}

