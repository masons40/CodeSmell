package smells;

/**
 * Return hashmap of Switch Conditions -> Number Cases
 */
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

public class MessageChaining {
	private boolean smellPresent = false;

	public MessageChaining(){
	}

	public HashMap<String, Integer> getMessageChains(ArrayList<SLFile> files){
		HashMap<String, Integer>  messageChains = new HashMap<>();
		Pattern pattern = Pattern.compile("\\.([a-zA-Z0-9]+)(\\(.*?\\))");
		int chainCount=0;

		for(SLFile f : files) {
			for (SLClass clazz : f.getClasses()) {
				for (SLMethod m : clazz.getMethods()) {
					for (String line : m.getMethodBody()) {
						line = line.replaceAll("\\s+", "");
						chainCount = 0;
						Matcher matcher = pattern.matcher(line);
						while (matcher.find()) {
							chainCount++;
						}
						if (chainCount > 1) {
							messageChains.put(line, chainCount);
						}
					}
				}
			}
		}
		smellPresent = messageChains.size()>0;
		return messageChains;
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
