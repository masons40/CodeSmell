/**
 * Abstract class for all smell detecting classes
 */

package Smells;

import files.SLClass;

import java.io.File;
import java.util.ArrayList;

abstract class SmellDetector<T> {
	protected static int lineCount;

	abstract ArrayList<T> evaluate (File file, SLClass originalClass) throws Exception;
}
