package files;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MethodDetector {

    private ArrayList<SLMethod> methods = new ArrayList<>();
    String name = ""; //string will store name of the method
    String accessModifier = ""; //string will store access modifier of the method
    String returnType = ""; //string will store return type of the method
    ArrayList<String> parameters = new ArrayList<>(); //parameters stored as an array list of strings

    //Method takes in a file reads through it and returns an arrayList of methods

    public void methodCreator(ArrayList<String> firstLineSplit, String firstLine){
        name = findMethodName(firstLineSplit);
        accessModifier = findAccessModifier(firstLineSplit);
        returnType = findReturnType(firstLineSplit,name);
        parameters = findParameters(firstLine);

        //methods.add(new SLMethod(name, accessModifier, returnType, parameters));
    }
//    public ArrayList<SLMethod> getMethods(File file) throws Exception {
//
//        ArrayList<SLMethod> methods = new ArrayList<>();
//
//        //2 buffer readers to read through 2 lines at once
//        //BufferedReader br1 = new BufferedReader(new FileReader(file));
//        //BufferedReader br2 = new BufferedReader(new FileReader(file));
//
//        //String firstLine;
//        //String secondLine;
//
//        //loop till end of file
//        while ((firstLine = br1.readLine()) != null) {
//            secondLine = br2.readLine();
//            String name = ""; //string will store name of the method
//            String accessModifier = ""; //string will store access modifier of the method
//            String returnType = ""; //string will store return type of the method
//            ArrayList<String> parameters = new ArrayList<String>(); //parameters stored as an array list of strings
//
//            String[] firstLineSplit = firstLine.split(" "); //split first line into array of strings at " "
//
//            //if the line is a method declaration then create a new method and add it to arrayList to be returned
//            if(isMethodDeclaration(firstLine, secondLine)){
//                name = findMethodName(firstLineSplit);
//                accessModifier = findAccessModifier(firstLineSplit);
//                returnType = findReturnType(firstLineSplit,name);
//                parameters = findParameters(firstLine);
//
//                methods.add(new SLMethod(name, accessModifier, returnType, parameters));
//
//            }
//            //if line follows the declaration of a constructor create a new method and add this to arrayList of methods
//            else if(isConstructorDeclaration(firstLine,secondLine,file.getName())){
//                name = findMethodName(firstLineSplit);
//                parameters = findParameters(firstLine);
//                methods.add(new SLMethod(name,accessModifier,returnType,parameters));
//            }
//
//        }
//        return methods;
//    }

    //method takes in 2 lines and checks if they are a method declaration

        public static boolean isMethodDeclaration(String firstLine, String secondLine){
            //create strings containing lines of the file without spaces
            String firstLineWithoutSpaces = firstLine.replaceAll("\\s", "");
            firstLineWithoutSpaces = firstLineWithoutSpaces.replaceAll("\\t","");
            String secondLineWithoutSpaces = "";

            if(secondLine!=null) {
                secondLineWithoutSpaces = secondLine.replaceAll(" ", "");
                secondLineWithoutSpaces = secondLineWithoutSpaces.replaceAll("\\t","");
            }

            /*Case 1:
             * (accessModifiers) (returnType/no returnType) (name)(parameters){
             */
            if(firstLineWithoutSpaces.matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)+(.+)\\(.*\\).*\\{")){
                return true;
            }
            /*Case 2:
             * (returnType) (name)(parameters){
             */
            else if(firstLine.matches("\\s*([a-zA-Z]+)\\s+([a-zA-Z]+)\\s*\\(.*\\)\\s*.*\\s*\\{\\s*") && !firstLine.contains("else if")){
                return true;
            }
            /*Case 3:
             * (accessModifiers) (returnType/no returnType) (name)(parameters)
             * {
             */
            else if(firstLineWithoutSpaces.matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)+(.+)\\(.*\\).*") && secondLineWithoutSpaces.startsWith("{")){
                return true;
            }
            /*Case 4:
             * (returnType) (name)(parameters)
             * {
             */
            else if(firstLine.matches("\\s*([a-zA-Z]+)\\s+([a-zA-Z]+)\\s*\\(.*\\)\\s*.*\\s*") && !firstLine.contains("else if") && secondLineWithoutSpaces.startsWith("{")){
                return true;
            }
            else {
                return false;
            }
        }

    //method takes in 2 lines and checks if they are a method declaration
    public static boolean isConstructorDeclaration(String firstLine, String secondLine, String fileName){
        //create strings containing lines of the file without spaces
        String firstLineWithoutSpaces = firstLine.replaceAll(" ", "");
        firstLineWithoutSpaces = firstLineWithoutSpaces.replaceAll("\t","");
        String secondLineWithoutSpaces = "";

        if(secondLine!=null) {
            secondLineWithoutSpaces = secondLine.replaceAll(" ", "");
            secondLineWithoutSpaces = secondLineWithoutSpaces.replaceAll("\t","");
        }

        // name of the class which we will use to check if line is constructor declaration
        String className = fileName.split("\\.")[0];

        /*Case 1:
         * (constructorName)(parameters){
         */
        if(firstLineWithoutSpaces.matches("([a-zA-Z]+)\\(.*\\)\\{") && firstLineWithoutSpaces.split("\\(")[0].equals(className)){
            return true;
        }
        /*Case 2:
         * (constructorName)(parameters)
         * {
         */
        else if(firstLineWithoutSpaces.matches("([a-zA-Z]+)\\(.*\\)") && firstLineWithoutSpaces.split("\\(")[0].equals(className) && secondLineWithoutSpaces.startsWith("{")){
            return true;
        }
        else{
            return false;
        }
    }

    //method finds the name of a method using a string array of each part of the method declaration split at " "

    private static String findMethodName(ArrayList<String> firstLineSplit){
        String name = "";
        for(String s: firstLineSplit) {
            /*if the string contains "(" and it doesn't start with the bracket the name
             *will be before the bracket in that string
             */
            if (s.contains("(") && !s.startsWith("(")) {
                name = s.split("\\(")[0];
            }
            //else if the string starts with "(" then previous string in array contains the name
            else if (s.startsWith("(")) {
                name = firstLineSplit.get(firstLineSplit.indexOf(s) - 1);
            }
        }
        return name;
    }

    //method finds the access modifiers of a method using a string array of each part of the method declaration split at " "

    private static String findAccessModifier(ArrayList<String> firstLineSplit){
        String accessor = "";
        //if string matches one of the access modifiers add this modifier to the string
        for(String s: firstLineSplit) {
            if (s.matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)")) {
                accessor += " " + s;
            }
        }
        return accessor;
    }

    public static String findReturnType(ArrayList<String> firstLineSplit, String name){

        String returnType = "";

        for(int i = 0; i<firstLineSplit.size(); i++) {
            //if string is an accessModifier and next string in array is not then next string is the return type
            if (firstLineSplit.get(i).matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)") && !firstLineSplit.get(i + 1).matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)") && !firstLineSplit.get(i + 1).contains(name)) {
                if(firstLineSplit.get(i).matches("HashMap<.*,")){
                    returnType = firstLineSplit.get(i + 1) + firstLineSplit.get(i + 2);
                }
                else{
                    returnType = firstLineSplit.get(i + 1);
                }
            }
        }
        //if no return type has been found it means there is no accessor and therefor
        //the return type will be the first word in the declaration or there is no return type
        if(returnType.equals("")){
            for (String r: firstLineSplit){
                if(!r.equals("") && !r.matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)")){
                    returnType = r;
                    break;
                }
                else if(!r.equals("") && r.matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)")){
                    break;
                }
            }
        }
        return returnType;
    }

    private static ArrayList<String> findParameters(String firstline){
        ArrayList<String> parameters = new ArrayList<String>();
        String allParams = "";
        //split the first line at "(" we will be concerned with the second half of this split
        String[] splitAtBracket = firstline.split("\\(");

        for (String x:splitAtBracket) {
            //if closing bracket is found parameters are all before this closing bracket
            if(x.contains(")") && x.split("\\)").length>0){
                allParams = x.split("\\)")[0];
            }
        }

        parameters.addAll(Arrays.asList(allParams.split(",")));

        return parameters;
    }

    public void clearMethodDetector(){
        methods.clear();
        name = ""; //string will store name of the method
        accessModifier = ""; //string will store access modifier of the method
        returnType = ""; //string will store return type of the method
        parameters.clear(); //parameters stored as an array list of strings
    }
    public ArrayList<SLMethod> getMethods(){
        return methods;
    }
}