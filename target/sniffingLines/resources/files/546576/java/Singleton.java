package dummy;

import java.util.Hashtable;
public class Singleton{

	private Singleton()	{}

	private static Singleton single_instance = null;

	//Thread-safe methods that return instance of Singleton class IFF none already exist
	public static synchronized Singleton getInstance() {
		if (single_instance == null)
			single_instance = new Singleton();
		return single_instance;
	}


}
