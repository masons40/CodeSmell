//Static Class to detect maximum depth of conditional blocks for each method
package smells;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrowHead extends SmellDetector{

	String conditionalBlockType = String.format("(if|else|elseif|switch|do|for|try|do)");

	public ArrowHead() {}

	//Return HashMap of FirstCondition(String) -> Maximum Depth Reached(Int)
	public HashMap<String, Integer> evaluate(File file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(file));
		HashMap<String, Integer> maxDepthsOfClass = new HashMap<>(); //Condition -> Max Conditional Depth
		String line;

		while ((line = br.readLine()) != null) {
			//TODO: file may already have no spaces after going through parser?
			line = line.replaceAll("\\s+", "");

			if (isConditionalBlock(line)){ //Enter main condition block
				String mainCondition = findCondition(line);
				int maxDepth = countDepth(br, line);
				maxDepthsOfClass.put(mainCondition, maxDepth);
			}

		}
		br.close();

		return maxDepthsOfClass;
	}

	//Count depth of conditional blocks within a conditional block
	int countDepth(BufferedReader br, String line) throws Exception {
		Stack<String> currConditions = new Stack<>();
		String condition = findCondition(line);
		currConditions.push(condition);
		int maxDepth = 1; //=currConditions.size();
		boolean expectOpenBracket = !endsWithOpenBracket(line); //if line = "if(..) {"

		while(!currConditions.isEmpty() && (line = br.readLine()) != null) { //While the br reads within the main condition block
			line = line.replaceAll("\\s+", ""); //TODO remove with filter
			if(expectOpenBracket && !endsWithOpenBracket(line) && !isEmpty(line)) { //previous condition line was not followed by "{"
				currConditions.pop(); //Remove from stack
			}

			//Search for conditional block in the line
			if(isConditionalBlock(line)) {
				//Extract condition statement within brackets
				condition = findCondition(line);
				currConditions.push(condition);
				expectOpenBracket = !endsWithOpenBracket(line);

			} else {
				expectOpenBracket = false;
			}

			Pattern pattern = Pattern.compile("[^}]*}");
			Matcher matcher = pattern.matcher(line);
			int numCloseBrackets = 0;
			while (matcher.find())
				numCloseBrackets++;

			if(numCloseBrackets>0) {
				for(;numCloseBrackets>0;numCloseBrackets--) {
					currConditions.pop();
				}
			}

			maxDepth = Math.max(maxDepth,currConditions.size());
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
		return (line.matches(conditionalBlockType+"\\(.*\\).*"));
	}

	private boolean endsWithOpenBracket(String line) {
		return (line.matches(".*\\{\\Z"));
	}

	private boolean isEmpty(String line) {
		return line.matches("^[ \t\r\n]*\\Z");
	}

	private boolean endsWithCloseBracket(String line) {
		return (line.matches(".*\\}\\Z"));
	}


}
