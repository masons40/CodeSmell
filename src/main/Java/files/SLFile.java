package files;

import java.util.ArrayList;

public class SLFile{

	private String fileName= "";
	private ArrayList<SLInterface> fileInterfaces = null;
	private ArrayList<SLEnum> fileEnums = null;
	private ArrayList<SLClass> fileClasses = null;
	private ArrayList<SLVariable> fileVariables = null;
	private int commentCount = 0;

	public void setName(String name){
		fileName = name;
	}

	public String getName(){
		return fileName;
	}

	public void setCommentCount(int count){
		commentCount=0;
	}
	public int getCommentCount(){
		return commentCount;
	}

	public void addToInterfaces(SLInterface slInterface){
		fileInterfaces.add(slInterface);
	}

	public void addToEnums(SLEnum slEnum){
		fileEnums.add(slEnum);
	}

	public void addToClasses(SLClass slClass){
		fileClasses.add(slClass);
	}

	public void addToVariables(SLVariable slVariable){
		fileVariables.add(slVariable);
	}

	public void setInterfaces(ArrayList<SLInterface> slInterface){
		fileInterfaces= slInterface;
	}

	public void setEnums(ArrayList<SLEnum> slEnum){
		fileEnums = slEnum;
	}

	public void setClasses(ArrayList<SLClass> slClass){
		fileClasses = slClass;
	}

	public void setVariables(ArrayList<SLVariable> slVariable){
		fileVariables = slVariable;
	}

	public ArrayList<SLInterface> getInterfaces(){
		return fileInterfaces;
	}

	public ArrayList<SLEnum> getEnums(){
		return fileEnums;
	}

	public ArrayList<SLClass> getClasses(){
		return fileClasses;
	}

	public ArrayList<SLVariable> getVariables(){
		return fileVariables;
	}

}
