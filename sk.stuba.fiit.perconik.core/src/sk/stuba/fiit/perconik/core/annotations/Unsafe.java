package sk.stuba.fiit.perconik.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import sk.stuba.fiit.perconik.core.Registrable;

/**
 * Indicates that a {@link Registrable} is unsafe and should be treated
 * accordingly by the core service implementations.
 * 
 * <p>In general all not thread safe implementations should be marked as
 * unsafe.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Unsafe
{
	/**
	 * Optional description of the unsafe feature.
	 */
	String value() default "";
}
