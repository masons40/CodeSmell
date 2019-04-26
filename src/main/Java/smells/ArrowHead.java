package  smells;

import files.SLClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrowHead extends SmellDetector{

	String conditionalBlockType = String.format("(if|else|elseif|switch|do|for|try|do)");

	public ArrowHead() {}

	public ArrayList<ArrowHeadSmell> evaluate(File file, SLClass originalClass) throws Exception{
		lineCount = 0;
		int startLineCount = 0;
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<ArrowHeadSmell> ArrowHeadSmells = new ArrayList<>(); //Condition -> Max Conditional Depth
		String line="";

		while ((line = br.readLine()) != null) {
			line = line.replaceAll("\\s+", "");

			if (isConditionalBlock(line)){ //Enter main condition block
				startLineCount = lineCount;
				String mainCondition = findCondition(line);
				int maxDepth = countDepth(br, line);
				ArrowHeadSmell newSmell = new ArrowHeadSmell(mainCondition, maxDepth,
															startLineCount, lineCount, originalClass);
				ArrowHeadSmells.add(newSmell);
			}
			lineCount++;
		}
		br.close();
		return ArrowHeadSmells;
	}

	//Count depth of conditional blocks within a conditional block
	int countDepth(BufferedReader br, String line) throws Exception {
		/*  Create stack to track current condition
		    1 ->    if condition ends with "{" (lasting)
			0 ->    if condition does not ends with "{" (temp)
		 */
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

		while(!currConditions.isEmpty() && (line = br.readLine()) != null) { //While the br reads within the main condition block
			line = line.replaceAll("\\s+", ""); //TODO remove with filter
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
			lineCount++;
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
