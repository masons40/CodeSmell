package nosejob;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation represents a method that doesn't change
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Invariant {

}
