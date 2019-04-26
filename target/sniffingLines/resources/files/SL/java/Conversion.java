package nosejob;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation that gives the default temperature min and max values for the methods
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Conversion {

    double min() default -100;
    double max() default 1000;
}
