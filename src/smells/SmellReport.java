/*
	Abstract class for what is returned by each smell detector
 */

package Smells;

import files.SLClass;
import java.io.File;
import java.util.ArrayList;

abstract class SmellReport{
	private int startLine;
	private int endLine;
	private SLClass originalClass;

	public SmellReport(/*...*/SLClass originalClass, int startLine, int endLine){
		this.startLine = startLine;
		this.endLine = endLine;
		this.originalClass = originalClass;
	}

	public SLClass getOriginalClass() { return originalClass; }

	public int getStartLine() { return  startLine; }

	public int getEndLine() { return  endLine; }
}