package  smells;

import files.SLClass;
import files.SLFile;
import files.SLMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrowHead {

	String conditionalBlockType = String.format("(if|else|elseif|switch|do|for|try|do)");
	private int currLine;
	private boolean smellPresent = false;

	public ArrowHead() {}

	public HashMap<String, Integer> getArrowHeads(ArrayList<SLFile> files){
		String line = "";
		HashMap<String, Integer> ArrowHeadSmells = new HashMap<>(); //Condition -> Max Conditional Depth
		for(SLFile f : files) {
			for (SLClass clazz : f.getClasses()) {
				for (SLMethod m : clazz.getMethods()) {
					ArrayList<String> fileLines = m.getMethodBody();
					int methodSize = fileLines.size();
					for (currLine = 0; currLine < methodSize; currLine++) {
						line = fileLines.get(currLine);
						line = line.replaceAll("\\s+", "");
						line = line.replaceAll("\\t+", "");
						if (isConditionalBlock(line)) { //Enter main condition block
							String mainCondition = findCondition(line);
							int maxDepth = countDepth(fileLines, methodSize);
							ArrowHeadSmells.put(mainCondition, maxDepth);
						}
					}
				}
			}
		}
		smellPresent = ArrowHeadSmells.size()>1;
		return ArrowHeadSmells;
	}

	//Count depth of conditional blocks within a conditional block
	int countDepth(ArrayList<String> methodBody, int methodSize) {
		/*  Create stack to track current condition
		    1 ->    if condition ends with "{" (lasting)
			0 ->    if condition does not ends with "{" (temp)
		 */
		String line = methodBody.get(currLine);
		Stack<Integer> currConditions = new Stack<>();
		boolean expectOpenBracket = false;
		//String condition = findCondition(line);
		if(endsWithOpenBracket(line))   //lasting
			currConditions.push(1);
		else {                         //temp
			currConditions.push(0);
			expectOpenBracket = true;
		}
		int maxDepth = currConditions.size();

		while(!currConditions.isEmpty() && currLine<methodSize) { //While the br reads within the main condition block
			line = methodBody.get(currLine);
			line = line.replaceAll("\\s+", "");
			line = line.replaceAll("\\t+", "");
			if(expectOpenBracket) { //Previous temp condition was actually lasting
				if(!currConditions.isEmpty() && startsWithOpenBracket(line)) {
					currConditions.pop();
					currConditions.push(1);
				}
			}

			if(!isConditionalBlock(line)){
				while(!currConditions.isEmpty() && currConditions.peek()==0)
					currConditions.pop();
			}

			if(startsWithCloseBracket(line)) {
				if(!currConditions.isEmpty() && currConditions.peek()==1)
					currConditions.pop();
				while(!currConditions.isEmpty() && currConditions.peek()==0)
					currConditions.pop();
			}

			expectOpenBracket = false;
			//Search for conditional block in the line
			if(isConditionalBlock(line)) {
				//Extract condition statement within brackets
				if(endsWithOpenBracket(line))   //lasting
					currConditions.push(1);
				else {                         //temp
					currConditions.push(0);
					expectOpenBracket = true;
				}
			}

			maxDepth = Math.max(maxDepth,currConditions.size());
			currLine++;
		}
		return maxDepth;
	}

	//Return ((condition)) between parenthesis
	private String findCondition(String line) {
		String condition = "";
		Matcher m = Pattern.compile("\\(([^()]+)\\)").matcher(line);
		if(m.find())
			condition = m.group(1);
		return condition;
	}

	//Does this line contain "if (...)" or something similar?
	private boolean isConditionalBlock(String line){
		return (line.matches("\\{?"+conditionalBlockType+"\\(.*\\).*"));
	}

	private boolean endsWithOpenBracket(String line) {
		return (line.matches(".*\\{\\Z"));
	}

//	private boolean isEmpty(String line) {
//		return line.matches("^[ \t\r\n]*\\Z");
//	}

	private boolean endsWithCloseBracket(String line) {
		return (line.matches(".*^((?!\\{).)*\\}$"));
	}

	private boolean startsWithOpenBracket(String line) {
		return(line.matches("^\\{.*"));
	}

	private boolean startsWithCloseBracket(String line) {
		return(line.matches("^\\}.*"));
	}


}
