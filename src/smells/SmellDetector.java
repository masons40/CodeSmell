/**
 * Abstract class for all smell detecting classes
 */

package smells;

import files.SLClass;

import java.io.File;
import java.util.ArrayList;

abstract class SmellDetector<T> {
	protected static int lineCount;
	//TODO change return to either Report or Hashmpap<String, Float>?
	abstract ArrayList<T> evaluate (File file, SLClass originalClass) throws Exception;
}
