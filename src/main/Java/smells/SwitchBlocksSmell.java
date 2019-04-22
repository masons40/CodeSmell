package Smells;

import files.SLClass;
import Smells.SmellReport;
public class SwitchBlocksSmell extends SmellReport {

	private String condition;
	private int numCases;

	public SwitchBlocksSmell(String condition, int numCases, int startLine, int endLine, SLClass originalClass) {
		super(originalClass, startLine, endLine);
		this.condition = condition;
		this.numCases= numCases;
	}
}