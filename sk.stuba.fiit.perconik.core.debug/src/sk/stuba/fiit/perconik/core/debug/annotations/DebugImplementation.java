package sk.stuba.fiit.perconik.core.debug.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sk.stuba.fiit.perconik.core.Registrable;

/**
 * Indicates that a {@link Registrable} is a debug implementation and should
 * be treated accordingly by the core service implementations.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DebugImplementation {
}
