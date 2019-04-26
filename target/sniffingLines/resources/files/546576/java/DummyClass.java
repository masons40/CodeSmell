/**
 * Useless class that will be tested in Main
 */

package dummy;
import java.lang.Math;

public class DummyClass {
	//Primitive fields
	private int myInt = 1;
	private float myFloat = 1;
	private boolean myBool = true;
	private char myChar = 'c';
	private byte myByte = 1;
	private long myLong = 1;
	private short myShort = 1;
	private double myDouble = 1.0;

	//Reference fields
	private String myString = "abc";
	private Time myTime = new Time(1,2,3);

	//Public methods (3)
	public void foo(int arg1, float arg2, Time arg3, int[] arg4) {}

	public float publicIntMethod(float arg1, float arg2) {
		return Math.min(arg1,arg2);
	}

	public Time publicTimeMethod() {
		return myTime;
	}

	//Private methods (4)
	private float privateIntMethod(float arg1, float arg2) {
		return Math.min(arg1,arg2);
	}

	private Time privateTimeMethod() {
		return myTime;
	}

	private void bar() {
		//Do nothing
	}

	private double baz(double arg1, double arg2) {
		return arg1 + arg2;
	}

}
