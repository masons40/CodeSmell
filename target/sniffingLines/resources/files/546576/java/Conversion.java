/*
	Annotation for any method that takes in one temperature object
	and then returns a double associated with another temperature
 */

package nosejob;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Conversion {
	double min() default -100;
	double max() default +10000;
}
