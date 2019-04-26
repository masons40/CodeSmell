/**
 *  Uses Java's lazy evaluation of assignments, to
 *   ensure only one instance within a single-threaded enviroment
 */

package dummy;

public class LazySingleton {

	private LazySingleton(){}

	private static class SingletonHelper{
		private static final LazySingleton INSTANCE = new LazySingleton();
	}

	public static LazySingleton getInstance(){
		return SingletonHelper.INSTANCE;
	}

}
