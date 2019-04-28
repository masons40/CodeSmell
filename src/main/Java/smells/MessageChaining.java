//package smells;
//
///**
// * Return hashmap of Switch Conditions -> Number Cases
// */
//import files.SLClass;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Stack;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class MessageChaining {
//
//	public MessageChaining(){
//	}
//
//	public HashMap<String, Integer> evaluate(File file, SLClass originalClass) throws Exception{
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		ArrayList<MessageChainingSmell> messageChains = new ArrayList<>();
//		Pattern pattern = Pattern.compile("\\.([a-zA-Z0-9]+)(\\(.*?\\))");
//		String line="";
//		int chainCount;
//
//		while ((line = br.readLine()) != null) {
//			line = line.replaceAll("\\s+", "");
//			chainCount = 0;
//
//			Matcher matcher = pattern.matcher(line);
//
//			while(matcher.find()) {
//				chainCount++;
//				System.out.println(chainCount);
//				System.out.println(matcher.group(1));
//			}
//
//			if(chainCount>0) {
//				messageChains.add(new MessageChainingSmell(line, chainCount, lineCount, lineCount, originalClass));
//			}
//			lineCount++;
//		}
//
//		return messageChains;
//	}
//
//	//Return ((condition)) between parenthesis
//	private String findCondition(String line) {
//		String condition = "";
//		Matcher m = Pattern.compile("\\(([^()]+)\\)").matcher(line);
//		if(m.find())
//			condition = m.group(1);
//		return condition;
//	}
//}
