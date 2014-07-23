package sk.stuba.fiit.perconik.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.Registrable;

/**
 * Indicates that a {@link Registrable} type is internal and should not be
 * considered as a resolvable type of a registrable instance.
 * 
 * @see Listeners#resolveTypes(Listener)
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Internal
{
}
