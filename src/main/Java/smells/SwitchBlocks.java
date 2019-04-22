/**
 * Return hashmap of Switch Conditions -> Number Cases
 */

package Smells;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Smells.SwitchBlocksSmell;
import files.SLClass;


public class SwitchBlocks extends SmellDetector{

	public SwitchBlocks(){
	}

	public ArrayList<SwitchBlocksSmell> evaluate(File file, SLClass originalClass) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<SwitchBlocksSmell> switchBlocks = new ArrayList<>(); //Variable -> Number of Cases
		Stack<Character> brackets = new Stack<>();
		Pattern openBracket = Pattern.compile("[^\\{]*\\{");
		Pattern closeBracket = Pattern.compile("[^\\}]*\\}");
		String line="", condition="";
		boolean readingSwitchBlock = false;
		int numCases=0;
		int startLine = 0;

		while ((line = br.readLine()) != null) {
			line = line.replaceAll("\\s+", "");
			if(line.matches("switch\\(.*")) { //If line contains keyword "switch"
				readingSwitchBlock= true;
				startLine = lineCount;
				//Extract switch variable within parenthesis
				condition = findCondition(line);
			}
			if (readingSwitchBlock) {
				if(line.startsWith("case") || line.startsWith("default")) {
					numCases++;
				}
				Matcher openMatcher = openBracket.matcher(line);
				while (openMatcher.find()) {
					brackets.push('{');
				}
				Matcher matcher = closeBracket.matcher(line);
				while (matcher.find()) {
					brackets.pop();
				}
				if(brackets.isEmpty()) { //reached end of switch block
					SwitchBlocksSmell newSmell = new SwitchBlocksSmell(condition, numCases, startLine, lineCount, originalClass);
					switchBlocks.add(newSmell);
					numCases=0;
					startLine=0;
					readingSwitchBlock = false;
				}
			}
			lineCount++;
		}

		return switchBlocks;
	}

	//Return ((condition)) between parenthesis
	private String findCondition(String line) {
		String condition = "";
		Matcher m = Pattern.compile("\\(([^()]+)\\)").matcher(line);
		if(m.find())
			condition = m.group(1);
		return condition;
	}
}
