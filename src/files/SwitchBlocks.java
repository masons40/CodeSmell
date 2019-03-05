/* Collection of SwitchBlocks to be assigned to each java source file */
package files;

import java.io.*;
import java.util.HashMap;

public class SwitchBlocks {
	static public HashMap<String, SwitchBlock> switch_blocks = new HashMap();
	public static String fileName = "";

	SwitchBlocks(String fileName) {
		this.fileName = fileName;
	}

	static void addSwitchBlock(String switch_var, SwitchBlock switch_block) {
		switch_blocks.put(switch_var, switch_block);
	}

	public static void smellSwitchBlocks() throws IOException {
		File file = new File(fileName);
		String switch_var=""; //Most recent switch variable observed
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
		boolean readingSwitchBlock = false;
		while ((line = br.readLine()) != null) {

			if(line.contains("switch")) { //If line contains keyword "switch"
				readingSwitchBlock= true;
				//Extract switch variable within parenthesis
				switch_var = line.substring(line.indexOf("(") + 1, line.indexOf(")")); //variable name
				SwitchBlock switch_block = new SwitchBlock(switch_var);
				addSwitchBlock(switch_var, switch_block);
			}
			if(readingSwitchBlock && line.contains("case")) {
				switch_blocks.get(switch_var).incrementNumCases();
			}
			if(line.indexOf("}") >= 0) {
				readingSwitchBlock=false;
			}
		}
		//System.out.println(switch_blocks.toString());
	}

}