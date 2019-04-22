package smells;
import files.SLClass;

import java.io.*;
import java.util.ArrayList;

/*
A God Class is a class which either knows or does too much
To see if a class is a god class or not, certain metrics will be needed to detect
this. We will try to detect this by looking at the number of lines and the number
of methods in the class
 */
public class GodClasses {
    ArrayList<SLClass> classes;

    public GodClasses(ArrayList<SLClass> classes){
        this.classes = classes;
    }

    /*
    Firstly calculate the average god score of all the classes
    then compare the god score of each class to the average and if
    it has a score greater than 2 and a half time the average
     */
    public ArrayList<SLClass> findGodClasses() throws IOException {
        ArrayList<SLClass> godClasses = new ArrayList<>();

        double avgGodScore = 0;

        for (SLClass clazz:classes) {
            File file = new File("filepath" + clazz.getClassName());
            int numLines = getNumOfFileLines(file);

            int numMethods =  4;//clazz.getMethods().size();                   change in future
            avgGodScore += calculateGodScore(numLines,numMethods);
        }

        avgGodScore = avgGodScore/classes.size();

        for (SLClass clazz:classes) {
            File file = new File("filepath" + clazz.getClassName());
            int numLines = getNumOfFileLines(file);

            int numMethods = 44; //clazz.getMethods().size();                   change in future
            double godScore = calculateGodScore(numLines,numMethods);

            if(godScore >= (2.5*avgGodScore)){
                godClasses.add(clazz);
            }
        }

        return godClasses;
    }


    private double calculateGodScore(int numOfLines, int numOfMethods){
        return (numOfLines*0.1)+(numOfMethods*0.7);
    }

    private static int getNumOfFileLines(File file) throws IOException {
        if(file.exists()){
            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);

            int numLines=0;

            while (lnr.readLine() != null){
                numLines++;
            }

            lnr.close();
            return numLines;
        }
        else{
            System.out.println("File does not exist");
            return 0;
        }
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
