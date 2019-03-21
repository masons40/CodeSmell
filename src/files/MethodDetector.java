package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MethodDetector {

    //method checks a directory of java files and prints all of the methods declared in the java files
    public void checkForMethods(String directoryAddress) throws Exception {
        File dir = new File(directoryAddress);

        for (File file : dir.listFiles()) {
            ArrayList<SLMethod> methods = new ArrayList<>();

            if(file.getName().substring(file.getName().indexOf(".") + 1).equals("java")) {
                BufferedReader br1 = new BufferedReader(new FileReader(file));
                BufferedReader br2 = new BufferedReader(new FileReader(file));

                String firstLine;
                String secondLine = br2.readLine();

                while ((firstLine = br1.readLine()) != null) {
                    secondLine = br2.readLine();
                    String name = "";
                    String accessor = "";
                    String returnType = "";
                    ArrayList<String> parameters = new ArrayList<String>();
                    String[] firstLineSplit = firstLine.split(" ");

                    if(isMethod(firstLine, secondLine)){
                        name = findMethodName(firstLineSplit);
                        accessor = findAccessor(firstLineSplit);
                        returnType = findReturnType(firstLineSplit,name);
                        parameters = findParameters(firstLine);

                        methods.add(new SLMethod(name, accessor, returnType, parameters));

                    }
                    else if(isConstructor(firstLine,secondLine,file.getName())){
                        name = findMethodName(firstLineSplit);
                        parameters = findParameters(firstLine);
                        methods.add(new SLMethod(name,accessor,returnType,parameters));
                    }
                }

                System.out.println(file.getName());
                for (int i = 0; i < methods.size(); i++) {
                    System.out.println(methods.get(i).toString());
                }
                System.out.println();
            }
        }

    }

    public static boolean isMethod(String firstLine, String secondLine){
        String firstLineNoSpaces = firstLine.replaceAll(" ", "");
        firstLineNoSpaces = firstLineNoSpaces.replaceAll("\t","");
        String st2Copy = "";
        if(secondLine!=null) {
            st2Copy = secondLine.replaceAll(" ", "");
            st2Copy = st2Copy.replaceAll("\t","");
        }

        if(firstLineNoSpaces.matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)+(.+)\\(.*\\).*\\{")){
            return true;
        }
        else if(firstLine.matches("\\s*([a-zA-Z]+)\\s+([a-zA-Z]+)\\s*\\(.*\\)\\s*.*\\s*\\{\\s*") && !firstLine.contains("else if")){
            return true;
        }
        else if(firstLineNoSpaces.matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)+([a-zA-Z]+)([a-zA-Z]+)\\(.*\\).*") && st2Copy.startsWith("{")){
            return true;
        }
        else if(firstLine.matches("\\s*([a-zA-Z]+)\\s+([a-zA-Z]+)\\s*\\(.*\\)\\s*.*\\s*") && !firstLine.contains("else if") && st2Copy.startsWith("{")){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isConstructor(String firstLine, String secondLine, String fileName){
        String firstLineNoSpaces = firstLine.replaceAll(" ", "");
        firstLineNoSpaces = firstLineNoSpaces.replaceAll("\t","");
        String st2Copy = "";

        if(secondLine!=null) {
            st2Copy = secondLine.replaceAll(" ", "");
            st2Copy = st2Copy.replaceAll("\t","");
        }

        String className = fileName.split("\\.")[0];

        if(firstLineNoSpaces.matches("([a-zA-Z]+)\\(.*\\)\\{") && firstLineNoSpaces.contains(className)){
            return true;
        }
        else if(firstLineNoSpaces.matches("([a-zA-Z]+)\\(.*\\)") && firstLineNoSpaces.contains(className) && st2Copy.startsWith("{")){
            return true;
        }
        else{
            return false;
        }
    }

    public static String findMethodName(String[] firstLineSplit){
        String name = "";
        for(int i = 0; i < firstLineSplit.length; i++) {
            if (firstLineSplit[i].contains("(") && !firstLineSplit[i].startsWith("(")) {
                name = firstLineSplit[i].split("\\(")[0];
            } else if (firstLineSplit[i].startsWith("(")) {
                name = firstLineSplit[i - 1];
            }
        }
        return name;
    }

    public static String findAccessor(String[] firstLineSplit){
        String accessor = "";
        for(int i = 0; i < firstLineSplit.length; i++) {
            if (firstLineSplit[i].matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)")) {
                accessor += " " + firstLineSplit[i];
            }
        }
        return accessor;
    }

    public static String findReturnType(String[] firstLineSplit, String name){
        String returnType = "";
        for(int i = 0; i<firstLineSplit.length; i++) {
            if (firstLineSplit[i].matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)") && !firstLineSplit[i + 1].matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)") && !firstLineSplit[i + 1].contains(name)) {
                returnType = firstLineSplit[i + 1];
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

    public static ArrayList<String> findParameters(String firstline){
        ArrayList<String> parameters = new ArrayList<String>();
        String allParams = "";
        String[] splitAtBracket = firstline.split("\\(");

        for (String x:splitAtBracket) {
            if(x.contains(")") && x.split("\\)").length>0){
                allParams = x.split("\\)")[0];
            }
        }

        parameters.addAll(Arrays.asList(allParams.split(",")));

        return parameters;
    }
}