package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MethodDetector {

    //method checks a directory of java files and prints all of the methods declared in the java files
    public void checkForMethods(String directoryAddress) throws Exception {
        File dir = new File(directoryAddress);

        //loops through each file in the directory
        for (File fileDir : dir.listFiles()) {
            ArrayList<SLMethod> methods = new ArrayList<>();
            if(fileDir.getName().substring(fileDir.getName().indexOf(".") + 1).equals("java")) {
                BufferedReader br1 = new BufferedReader(new FileReader(fileDir));
                BufferedReader br2 = new BufferedReader(new FileReader(fileDir));

                String st1;
                String st2 = br2.readLine();

                while ((st1 = br1.readLine()) != null) {
                    st2 = br2.readLine();
                    String name = "";
                    String accessor = "";

                    if(isMethod(st1, st2)){
                        String[] subParts = st1.split(" ");
                        for(int i = 0; i < subParts.length; i++){
                            if(subParts[i].contains("(") && !subParts[i].startsWith("(")){
                                name = subParts[i].split("\\(")[0];
                            }
                            else if(subParts[i].startsWith("(")){
                                name = subParts[i-1];
                            }
                            if(subParts[i].matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)")){
                                accessor += " " + subParts[i];
                            }
                        }
                        methods.add(new SLMethod(name,accessor));
                    }

                }

                System.out.println(fileDir.getName());
                System.out.println(methods.size());
                for (int i = 0; i < methods.size(); i++) {
                    System.out.println(methods.get(i).getAccessor() + " " + methods.get(i).getName());
                }
                System.out.println();
            }
        }

    }

    public static boolean isMethod(String firstLine, String secondLine){
        String st1Copy = firstLine.replaceAll(" ", "");
        String st2Copy = "";
        if(secondLine!=null) {
            st2Copy = secondLine.replaceAll(" ", "");
        }

        if(st1Copy.matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)+([a-zA-Z]+)([a-zA-Z]+)\\(.*\\)\\{")){
            System.out.println(firstLine);
            return true;
        }
        if(firstLine.matches("\\s*([a-zA-Z]+)\\s+([a-zA-Z]+)\\s*\\(.*\\)\\s*\\{\\s*") && !firstLine.contains("else if")){
            System.out.println(firstLine);
            return true;
        }
        if(st1Copy.matches("(public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)+([a-zA-Z]+)([a-zA-Z]+)\\(.*\\)") && st2Copy.startsWith("{")){
            return true;
        }
        if(firstLine.matches("\\s*([a-zA-Z]+)\\s+([a-zA-Z]+)\\s*\\(.*\\)\\s*") && !firstLine.contains("else if") && st2Copy.startsWith("{")){
            return true;
        }
        return false;
    }

}