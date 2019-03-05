/*Switch Block object for each conditional variable */

package files;

public class SwitchBlock {
	private final String var_name;
	private int num_cases;

	SwitchBlock(String var_name) {
		this.var_name = var_name;
		this.num_cases = 0;
	}

	public void incrementNumCases() {
		num_cases++;
	}

	@Override
	public String toString() {
		return "Switch Block for " + var_name + " : " + num_cases + " cases";
	}
}
