package Smells;

import files.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class UnusedMethods {

    //Method takes in a hash map of methods to integer where integer value is the number of times a method has been used
    public HashMap<SLMethod, Integer> findMethodUsage(File file, HashMap<SLMethod, Integer> methodUsage) throws Exception {
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        BufferedReader br2 = new BufferedReader(new FileReader(file));

        String firstLine;
        String secondLine = br2.readLine();

        while ((firstLine = br1.readLine()) != null) {
            secondLine = br2.readLine();
            //for each method in the hashmap if that method is used in the file increment its use integer
            for (SLMethod method:methodUsage.keySet()) {
                if(firstLine.contains(method.getName()+"(") && !MethodDetector.isMethodDeclaration(firstLine,secondLine)){
                    int count = methodUsage.get(method);
                    count++;
                    methodUsage.put(method,count);
                }
            }
        }
        return methodUsage;
    }

}

