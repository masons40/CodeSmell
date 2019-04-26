package nosejob;

/*
	Annotation to be assigned to methods that returns
	the same temperature type as it's input
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Invariant {
	//Empty annotation
}
