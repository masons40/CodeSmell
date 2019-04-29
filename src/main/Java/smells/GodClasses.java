package smells;
import files.SLClass;
import files.SLFile;
import files.SLMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
A God Class is a class which either knows or does too much
To see if a class is a god class or not, certain metrics will be needed to detect
this. We will try to detect this by looking at the number of lines and the number
of methods in the class
 */
public class GodClasses {
    private transient ArrayList<SLFile> files;
    private int numberOfGodClasses = 0;
    private double averageGodScore = 0;
    private ArrayList<String> godClasses = new ArrayList<>();
    private ArrayList<String> nongodClasses = new ArrayList<>();
    private HashMap<String, Double> godScores = new HashMap<>();
    private boolean smellPresent = false;

    public GodClasses(ArrayList<SLFile> files) throws IOException {
        this.files = files;
        findGodClasses();
    }

    /*
    Firstly calculate the average god score of all the classes
    then compare the god score of each class to the average and if
    it has a score greater than 2 and a half time the average
     */
    public ArrayList<String> findGodClasses() throws IOException {
        double numberOfClasses=0.0;

        for (SLFile slFile: files) {
            ArrayList<SLClass> classes = slFile.getClasses();
            for (SLClass clazz : classes) {
                int numLines = getNumOfFileLines(clazz);
                int numMethods = clazz.getMethods().size();                  //change in future
                averageGodScore += calculateGodScore(numLines, numMethods);
                numberOfClasses++;
            }
        }

        averageGodScore /= numberOfClasses;

        for (SLFile slFile:files) {
            ArrayList<SLClass> classes = slFile.getClasses();
            for (SLClass clazz : classes) {
                int numLines = getNumOfFileLines(clazz);
                int numMethods = clazz.getMethods().size();                   //change in future
                double godScore = calculateGodScore(numLines, numMethods);
                godScores.put(clazz.getClassName(),godScore);
                if ((godScore >= (3.5 * averageGodScore)) || (numLines > 1500)) {
                    godClasses.add(clazz.getClassName());
                }
            }
        }

        numberOfGodClasses=godClasses.size();

        if (godClasses.size()>0) {
            smellPresent = true;
        }

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
