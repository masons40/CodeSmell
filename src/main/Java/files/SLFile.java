package files;

import java.util.ArrayList;

public class SLFile{

	private String fileName= "";
	private ArrayList<SLVariable> variables = new ArrayList<>();
	private ArrayList<SLMethod> methods = new ArrayList<>();
	private ArrayList<SLClass> classes = new ArrayList<>();
	private ArrayList<SLInterface> interfaces = new ArrayList<>();
	private ArrayList<SLEnum> enums = new ArrayList<>();
	private int commentCount = 0;

	public SLFile(String name, ArrayList classes, ArrayList methods, ArrayList variables, ArrayList interfaces, ArrayList enums, int commentCount){
		this.fileName = name;
		this.variables = variables;
		this.methods = methods;
		this.classes = classes;
		this.enums = enums;
		this.interfaces = interfaces;
		this.commentCount = commentCount;
	}

	public String getName(){
		return fileName;
	}

	public int getCommentCount(){
		return commentCount;
	}

	public ArrayList<SLInterface> getInterfaces(){
		return interfaces;
	}

	public ArrayList<SLEnum> getEnums(){
		return enums;
	}

	public ArrayList<SLClass> getClasses(){
		return classes;
	}

	public ArrayList<SLVariable> getVariables(){
		return variables;
	}

	public ArrayList<SLMethod> getMethods(){
		return methods;
	}

	public String toString(){
		return fileName + commentCount;
	}

}
