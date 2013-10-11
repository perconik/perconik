package sk.stuba.fiit.perconik.core.persistence.data;

import sk.stuba.fiit.perconik.core.persistence.AnnotableRegistration;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.ForwardingAnnotable;

/**
 * An abstract implementation of the {@link AnnotableRegistration} interface.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractAnnotableRegistration extends ForwardingAnnotable implements AnnotableRegistration
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected AbstractAnnotableRegistration()
	{
	}
}
