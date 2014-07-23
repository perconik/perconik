package sk.stuba.fiit.perconik.core.persistence;

import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotable;

/**
 * A {@code Registration} with annotations. This interface is an extension
 * to the {@code Registration} interface able to provide useful annotations
 * about the underlying registrable object or directly itself.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface AnnotableRegistration extends Annotable, Registration
{
}
