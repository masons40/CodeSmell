package Smells;

import files.SLClass;

public class MessageChainingSmell extends SmellReport{
	private String line;
	private int chainLength;

	public MessageChainingSmell(String line, int chainLength, int startLine, int endLine, SLClass originalClass) {
		super(originalClass, startLine, endLine);
		this.line = line;
		this.chainLength= chainLength; //e.g. obj.msg1(a).msg2(b).msg3(c) has length 3
	}

	public String getLine(){ return line; }

	public Integer getChainLength() { return chainLength; }
}
