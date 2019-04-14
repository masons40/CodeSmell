package smells;
import files.SLClass;

import java.io.*;
import java.util.ArrayList;

/*
A God Class is a class which either knows or does too much
To see if a class is a god class or not, a number of metrics will be needed to detect
this. We will try to detect this by seeing how much access a class has to foreign data,
how much the object does that doesn't relate to itself, the number of lines and the number
of methods in the class
 */
public class GodClasses {
    ArrayList<SLClass> classes;

    public GodClasses(ArrayList<SLClass> classes){
        this.classes = classes;
    }

    public ArrayList<SLClass> findGodClass() throws IOException {
        ArrayList<SLClass> godClasses = new ArrayList<>();

        double avgGodScore = 0;

        //calculate avg "godScore"
        for (SLClass clazz:classes) {
            File file = new File("filepath" + clazz.getName());
            int numLines = (int) file.length();
            int numMethods = clazz.getMethods().size();
            avgGodScore += calculateGodScore(numLines,numMethods);
        }

        avgGodScore = avgGodScore/classes.size();

        //check if each classes godScore is twice the size or more than the average
        for (SLClass clazz:classes) {
            File file = new File("filepath" + clazz.getName());
            int numLines = (int) file.length();
            int numMethods = clazz.getMethods().size();
            double godScore = calculateGodScore(numLines,numMethods);

            if(godScore >= (2*avgGodScore)){
                godClasses.add(clazz);
            }
        }

        return godClasses;
    }


    private double calculateGodScore(int numOfLines, int numOfMethods){
        return (numOfLines*0.1)+(numOfMethods*0.7);
    }

    /*
    If an object is Tightly Coupled it is reliant too much on different objects.
    A god class would be tightly coupled as having access to too much data fits part of the definition
    of a god class, metrics might include methods used from other classes, number of objects of other
    classes in the class
     */
    /*private boolean isTightlyCoupled(SLClass clss) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/markhartnett/Documents/CodeSnifferPractice/src/"+clss.getName()));
        String tempLine;
        int outsideMethodCalls = 0;

        ArrayList<Method> allMethods = new ArrayList<>();
        for (SLClass clazz: classes) {
            if(cls!=clazz) {
                allMethods.addAll(cls.getMethods());
            }
        }

        //search through the class itself and see how many outside methods are called
        while ((tempLine = br.readLine()) != null){
            for (Method method: allMethods) {
                if (tempLine.contains(method.getName() + "(")) {
                    outsideMethodCalls++;
                }
            }
        }

        return outsideMethodCalls>10;
    }*/

}
