package SmellTest;

import java.io.*;
import java.util.*;
public class RepeatedIfs {

    String fileName = "";

    public RepeatedIfs(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    /*File file = new File(fileName);
    Scanner in = new Scanner(file);
    HashMap<String, Integer> map = new HashMap<>();

        while(in.hasNextLine()){
            String tempKey = in.nextLine();

            if (tempKey.indexOf(';') >= 0) {
                if (map.containsKey(tempKey)) {
                    int count = map.get(tempKey);
                    count++;
                    map.put(tempKey, count);
                } else {
                    map.put(tempKey, 1);
                }
            }
        }

        for(String key: map.keySet()){
        //if(map.get(key) > 1){
            System.out.println("Line: "+key+"\nCount: "+map.get(key)+"\n");
        }
        */
}
