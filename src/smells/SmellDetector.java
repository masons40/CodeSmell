/**
 * Abstract class for all smell detecting classes
 */

package smells;

import java.io.File;
import java.util.HashMap;

abstract class SmellDetector<T> {
	//TODO change return to either Report or Hashmpap<String, Float>?
	abstract HashMap<T,T> evaluate (File file) throws Exception;
}
