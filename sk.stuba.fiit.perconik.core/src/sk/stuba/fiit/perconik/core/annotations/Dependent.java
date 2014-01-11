package sk.stuba.fiit.perconik.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import sk.stuba.fiit.perconik.core.Registrable;

/**
 * Indicates that a {@link Registrable} type is dependent on specific classes.
 * 
 * <p>In general all registrables that depend on classes from internal
 * packages, or classes from beta or unstable APIs should be also marked
 * as dependent and provided with a list of all such classes.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Dependent
{
	/**
	 * Class dependencies.
	 */
	Class<?>[] value();	
}
