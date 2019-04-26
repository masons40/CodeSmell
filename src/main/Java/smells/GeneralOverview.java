package smells;

import files.*;

import java.util.ArrayList;

public class GeneralOverview {

    private String companyName = "";
    private int numberOfClasses = 0;
    private int numberOfInterfaces = 0;
    private int numberOfMethods = 0;
    private int numberOfFiles = 0;
    private int numberOfClassVariables = 0;
    private int numberOfEnums = 0;
    private int numberOfComments = 0;
    private ArrayList<String> filenames = null;

    public GeneralOverview(ArrayList<SLFile> files, String companyName, ArrayList<String> filenames){
        this.filenames = filenames;
        this.companyName = companyName;
        for(SLFile file: files){
            numberOfClasses+=file.getClasses().size();
            numberOfMethods+=file.getMethods().size();
            numberOfInterfaces+=file.getInterfaces().size();
            numberOfClassVariables+=file.getVariables().size();
            numberOfEnums+=file.getEnums().size();
            numberOfComments+=file.getCommentCount();
        }
        numberOfFiles = files.size();
    }

    public String toString(){
        return "number of classes" + numberOfClasses + "number of methods" + numberOfMethods + "number of files" + numberOfFiles + "number of interfaces" + numberOfInterfaces +
                "number of enums" + numberOfEnums + "number of variables" + numberOfClassVariables + "number of comments" + numberOfComments;
    }
}
