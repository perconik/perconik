package sk.stuba.fiit.perconik.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a resource or listener functionality is not yet fully
 * supported and such implementations should be treated accordingly by
 * the core services.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Unsupported
{
	/**
	 * Optional descriptions of unsupported functionality.
	 */
	String[] value() default {};
}
