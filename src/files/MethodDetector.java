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

                    if (isMethod(st1, st2)) {
                        String[] parts = st1.split(" ");
                        for (int x = 0; x < parts.length; x++) {
                            if (parts[x].contains("(")) {
                                String[] subParts = parts[x].split("\\(");
                                if (!st1.replaceAll(" ", "").startsWith(subParts[0]) && !subParts[0].contains(".")) {
                                    methods.add(new SLMethod(subParts[0]));
                                }
                            }
                        }
                    }

                }

                //print out the file name and all of its methods
                System.out.println(fileDir.getName());
                System.out.println(methods.size());
                for (int i = 0; i < methods.size(); i++) {
                    System.out.println(methods.get(i).getName());
                }
                System.out.println();
            }
        }

    }

    //method to check if a line follows certain elements of a method signature
    public static boolean isMethod(String firstLine, String secondLine){
        String st1Copy = firstLine.replaceAll(" ", "");

        /*case when method declared like:
           methodName(){
               ....
           }
        */
        if (st1Copy.contains(")") && st1Copy.endsWith("{") && !st1Copy.contains("elseif")){// && !st1Copy.contains("if(") && !st1Copy.contains("for(") && !st1Copy.contains("while(")
            return true;
        }

        /*case when method declared like:
           methodName(){....}
        */
        else if (st1Copy.contains(")") && st1Copy.endsWith("}") && !st1Copy.contains("elseif")){// && !st1Copy.contains("if(") && !st1Copy.contains("for(") && !st1Copy.contains("while(")
            return true;
        }

        /*case when method declared like:
           methodName()
           {
               ....
           }
        */
        else if ((st1Copy.contains(")") && secondLine.replaceAll(" ","").startsWith("{")) && !st1Copy.contains("elseif")){// && !st1Copy.contains("if(") && !st1Copy.contains("for(") && !st1Copy.contains("while(")
            return true;
        }

        else {
            return false;
        }
    }

}