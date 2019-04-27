package smells;
import files.SLClass;
import files.SLFile;
import files.SLMethod;

import java.io.*;
import java.util.ArrayList;

/*
A God Class is a class which either knows or does too much
To see if a class is a god class or not, certain metrics will be needed to detect
this. We will try to detect this by looking at the number of lines and the number
of methods in the class
 */
public class GodClasses {
    private ArrayList<SLFile> files;
    private int numberOfGodClasses = 0;
    private int averageGodScore = 0;
    private ArrayList<SLClass> godClasses = new ArrayList<>();
    private ArrayList<Double> godScores = new ArrayList<>();

    public GodClasses(ArrayList<SLFile> files){
        this.files = files;
    }

    /*
    Firstly calculate the average god score of all the classes
    then compare the god score of each class to the average and if
    it has a score greater than 2 and a half time the average
     */
    public ArrayList<SLClass> findGodClasses() throws IOException {
        for (SLFile slFile: files) {
            ArrayList<SLClass> classes = slFile.getClasses();
            for (SLClass clazz : classes) {
                int numLines = getNumOfFileLines(clazz);
                int numMethods = clazz.getMethods().size();                  //change in future
                averageGodScore += calculateGodScore(numLines, numMethods);
            }
        }

        averageGodScore = averageGodScore/files.size();

        for (SLFile slFile:files) {
            ArrayList<SLClass> classes = slFile.getClasses();
            for (SLClass clazz : classes) {
                int numLines = getNumOfFileLines(clazz);
                int numMethods = clazz.getMethods().size();                   //change in future
                double godScore = calculateGodScore(numLines, numMethods);
                if ((godScore >= (3.5 * averageGodScore)) || (numLines > 700)) {
                    godClasses.add(clazz);
                }
            }
        }

        numberOfGodClasses=godClasses.size();
        return godClasses;
    }


    private double calculateGodScore(int numOfLines, int numOfMethods){
        return (numOfLines*0.1)+(numOfMethods*0.7);
    }

    private static int getNumOfFileLines(SLClass clazz){
        int numOfLines = 0;

        for (SLMethod method : clazz.getMethods()) {
            numOfLines+=method.getMethodBody().size();
        }

        return numOfLines;
    }
}
