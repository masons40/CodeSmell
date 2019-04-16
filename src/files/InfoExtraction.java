package files;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class InfoExtraction {

    private ArrayList<String> keywords = new ArrayList<>();
    private ArrayList<String> otherKeyWords = new ArrayList<>();
    private ArrayList<SLClass> classes = new ArrayList<>();
    private int currentIndex=-1;

    private ArrayList<String> classAccessTypes = new ArrayList<>();
    private String ext="extends", imp="implements";
    private String previousLine = "";
    private Stack pushDownClassAutomata = new Stack();

    public InfoExtraction(){
        classAccessTypes.add("final");
        classAccessTypes.add("abstract");
        classAccessTypes.add("strictfp");
        classAccessTypes.add("private");
        classAccessTypes.add("protected");
        classAccessTypes.add("static");
        classAccessTypes.add("public");

        keywords.add("abstract");
        keywords.add("native");
        keywords.add("final");
        keywords.add("static");
        keywords.add("strictfp");
        keywords.add("public");
        keywords.add("private");
        keywords.add("synchronized");

        otherKeyWords.add("if");
        otherKeyWords.add("for");
        otherKeyWords.add("switch");
    }

    public void checkIfClassLine(String line){

        if(classDetection(line)){
            if(previousLine.contains("class")){
                extractClassInfo(previousLine);
            }else{
                extractClassInfo(line);
            }

        }else if(methodDetection(line)){

        }else if(interfaceDetection(line)){
            if(line.contains("interface")){
                ArrayList<String> temp = lineStrip(Arrays.asList(line.split(" ")));
                String name = temp.get(temp.indexOf("interface")+1);
                ClassDetailsModify(name);
            }else{
                ArrayList<String> temp = lineStrip(Arrays.asList(previousLine.split(" ")));
                String name = temp.get(temp.indexOf("interface")+1);
                ClassDetailsModify(name);
            }
        }else if(enumDetection(line)){

        }else if(otherLineDetection(line)){

        }
        if(line.contains("}") && pushDownClassAutomata.peek()=="M"){
            System.out.println("->M");
            pushDownClassAutomata.pop();
        }else if(line.contains("}") && pushDownClassAutomata.peek()=="C"){
            System.out.println("->C");
            currentIndex--;
            pushDownClassAutomata.pop();
        }else if(line.contains("}") && pushDownClassAutomata.peek()=="O"){
            System.out.println("->O");
            pushDownClassAutomata.pop();
        }else if(line.contains("}") && pushDownClassAutomata.peek()=="I"){
            System.out.println("->I");
            pushDownClassAutomata.pop();
        }
        else if(line.contains("}") && pushDownClassAutomata.peek()=="E"){
            System.out.println("->E");
            pushDownClassAutomata.pop();
        }

        if(!(line.trim().isEmpty())){
            previousLine = line;
        }
    }

    private Boolean ClassLineCheck(String l){
        List<String> arr = Arrays.asList(l.split(" "));

        return lineStrip(arr).contains("class");
    }


    private Boolean LineCheck(String l){
        for(String s: keywords){
            if(l.contains(s)){
                return true;
            }
        }
        return false;
    }

    private Boolean OtherCheckLine(String current , String previousLine){
        for(String s: otherKeyWords){
            if(current.contains(s) || previousLine.contains(s)){
                return true;
            }
        }
        return false;
    }

    private Boolean classDetection(String line){
        if(line.contains("{") && (ClassLineCheck(line) || ClassLineCheck(previousLine))){
            pushDownClassAutomata.push("C");
            System.out.println("C");
            return true;
        }
        return false;
    }

    private Boolean methodDetection(String line){
        if(line.contains("{") && !(line.contains("class")) && (LineCheck(line ) || LineCheck(previousLine)) && !OtherCheckLine(line, previousLine) ){
            pushDownClassAutomata.push("M");
            System.out.println("M");
            return true;
        }
        return false;
    }

    private Boolean interfaceDetection(String line){
        if(line.contains("{") && (line.contains("interface") || previousLine.contains("interface"))){
            pushDownClassAutomata.push("I");
            System.out.println("I");
            return true;
        }
        return false;
    }

    private Boolean enumDetection(String line){
        if(line.contains("{") && (line.contains("enum") || previousLine.contains("enum"))){
            pushDownClassAutomata.push("E");
            System.out.println("E");
            return true;
        }
        return false;
    }
    private Boolean otherLineDetection(String line){
        if(line.contains("{") && !(line.contains("class")) && (!LineCheck(line) || !LineCheck(previousLine))){
            pushDownClassAutomata.push("O");
            System.out.println("O");
            return true;
        }
        return false;
    }

    private void extractClassInfo(String line){
        SLClass bef = new SLClass();
        ArrayList<String> classLineElements = lineStrip(Arrays.asList(line.split(" ")));
        for(String s: classLineElements){
            if(classAccessTypes.contains(s)){
                bef.setClassModfier(s);
                classLineElements.remove(s);
                bef.setClassBoolToTrue(s);
                break;
            }
        }
        bef.setClassName(classLineElements.get(classLineElements.indexOf("class") + 1));
        if(classLineElements.contains(ext)){
            bef.setExtendsName(classLineElements.get(classLineElements.indexOf(ext) +1));
        }
        if(classLineElements.contains(imp)){
            List<String> subL = classLineElements.subList(classLineElements.indexOf(imp)+1, classLineElements.indexOf("{"));
            for(String s: subL){
                if(!(s.equals(","))){
                    bef.setImplementsName(s);
                }
            }
        }
        bef.setClass();
        currentIndex++;
        classes.add(bef);
    }

    private ArrayList lineStrip(List listOfWords){
        ArrayList<String> list = new ArrayList<>();
        for(Object s: listOfWords){
            String ns = s.toString().replaceAll("\\s+","");
            list.add(ns);
        }
        return list;
    }

    private void ClassDetailsModify(String interfaceName){

        classes.get(currentIndex).setImplementsName(interfaceName);
    }


}