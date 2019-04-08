package smells;

public class Report {

	private int startLine, endLine = 0;
	private String name = "";
	private float value = 0;
	Report(String name, float value, int startLine, int endLine) {
		this.name = name;
		this.value = value;
		this.endLine = endLine;
		this.startLine = startLine;

	}

}
