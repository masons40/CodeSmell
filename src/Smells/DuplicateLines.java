package Smells;

import java.io.*;

public class DuplicateLines {

    String fileName;
    File inputFile;

    public DuplicateLines(String fileName, File inputFile) throws FileNotFoundException {
        this.fileName = fileName;
        this.inputFile = inputFile;
    }

    /*File file = new File(fileName);
    Scanner in = new Scanner(file);
    HashMap<String, Integer> map = new HashMap<>();

        while(in.hasNextLine()){
            String tempKey = in.nextLine();
            tempKey = tempKey.trim();

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
