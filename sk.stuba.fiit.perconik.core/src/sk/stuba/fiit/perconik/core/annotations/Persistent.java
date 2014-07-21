package sk.stuba.fiit.perconik.core.annotations;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sk.stuba.fiit.perconik.core.Registrable;

/**
 * Indicates that a {@link Registrable} is persistent and should be treated
 * accordingly by the core service implementations.
 * 
 * <p>In general all registrables that implement the {@link Serializable}
 * interface should be also marked as persistent.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Persistent
{
}
