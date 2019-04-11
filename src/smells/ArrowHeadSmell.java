package smells;

import files.SLClass;

public class ArrowHeadSmell extends SmellReport{

	private String condition;
	private int depth;
	private int startLine;
	private int endLine;
	private SLClass originalClass;

	public ArrowHeadSmell(String condition, int depth, int startLine, int endLine, SLClass originalClass) {
		super(originalClass, startLine, endLine);
		this.condition = condition;
		this.depth= depth;
	}
}
