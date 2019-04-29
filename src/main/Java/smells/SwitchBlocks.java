/**
 * Return hashmap of Switch Conditions -> Number Cases
 */

package smells;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import files.*;

public class SwitchBlocks{

	private HashMap<String, Integer> switchBlocks = new HashMap<>();
	private String caseRegex = String.format("case|default");
	private boolean smellPresent = false;

	public SwitchBlocks(){
	}

	public HashMap<String, Integer> getSwitchBlocks(ArrayList<SLFile> files) {
		boolean readingSwitchBlock = false;
		Stack<Character> brackets = new Stack<>();
		Pattern openBracket = Pattern.compile("[^\\{]*\\{");
		Pattern closeBracket = Pattern.compile("[^\\}]*\\}");
		int numCases=0;
		String newSwitchBlock = null;

		for(SLFile f : files) {
			for (SLClass clazz : f.getClasses()) {
				for (SLMethod m : clazz.getMethods()) {
					readingSwitchBlock = false;
					for (String line : m.getMethodBody()) {
						if (line.matches("(\\s|\\t)*(switch)(\\s|\\t)*\\(.*\\)(\\s|\\t)*\\{(\\s|\\t)*")) {
							readingSwitchBlock = true;
							newSwitchBlock = findCondition(line);
						}
						if(readingSwitchBlock) {
							if (line.contains("case")||line.contains("default")) {
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

							if (brackets.isEmpty()) { //reached end of switch block
								switchBlocks.put(newSwitchBlock, numCases);
								numCases = 0;
								readingSwitchBlock = false;
							}

						}
					}
				}
			}
		}
		smellPresent = switchBlocks.size()>0;
		return  switchBlocks;
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
