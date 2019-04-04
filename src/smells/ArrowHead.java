//Static Class to detect maximum depth of if blocks for each method
package smells;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;

public class ArrowHead extends SmellDetector{

	String conditionalBlockType = String.format("(if|else|ifelse|switch|while|do|for|try|do)");

	public ArrowHead() {}

	public HashMap<String, Float> evaluate(File file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;

		while ((line = br.readLine()) != null) {
			//String[] lineSplit =line.split("\\s+");

			if (isBlock(line)){
				System.out.println(line);
				int depth = 0;
				int maxDepth = countDepth(br, depth);
			}

		}
		br.close();

		return null;
	}

	int countDepth(BufferedReader br, int depth) {
		depth++;
		Stack<Character> currDepth = new Stack<>();

		return depth;
	}

	//Does this line contain "if (...)" or something similar?
	public boolean isBlock(String line){
		//Remove all whitespace
		line = line.replaceAll("\\s+", "");
		if (line.matches(conditionalBlockType+"\\(.*")) {
			return true;
		}
		else
			return false;
	}


}
