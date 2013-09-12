package sk.stuba.fiit.perconik.core.services.listeners;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.IllegalListenerClassException;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.AbstractProvider;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

/**
 * An abstract implementation of {@link ListenerProvider}. This class
 * implements the listener providing mechanism based on an underlying
 * {@code ClassLoader} of standard Java classes.
 * 
 * TODO doc providing process, class loading / instantiation
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractListenerProvider extends AbstractProvider implements ListenerProvider
{
	// TODO add javadocs
	
	/**
	 * Constructor for use by subclasses.
	 */
	protected AbstractListenerProvider()
	{
	}

	protected abstract ClassLoader loader();
	
	protected final Class<?> load(final String name) throws ClassNotFoundException
	{
		Preconditions.checkArgument(!name.isEmpty());
		
		return this.loader().loadClass(name);
	}
	
	protected final static Class<? extends Listener> cast(final Class<?> implementation)
	{
		if (!Listener.class.isAssignableFrom(implementation))
		{
			throw new IllegalListenerClassException("Class " + implementation.getName() + " is not assignable to " + Listener.class.getName());
		}
		
		if (implementation.isInterface() || implementation.isAnnotation())
		{
			throw new IllegalListenerClassException("Type " + implementation.getName() + " can not be an interface or an annotation");
		}
		
		return implementation.asSubclass(Listener.class);
	}
	
	protected final ListenerProvider parentOrFailure()
	{
		ListenerProvider parent = this.parent();
		
		if (parent == null)
		{
			throw new IllegalStateException("Provider hierarchy root reached");
		}
		
		return parent;
	}

	protected final <L extends Listener> L parentForClass(final Class<L> implementation, @Nullable final Exception cause)
	{
		try
		{
			return this.parentOrFailure().forClass(implementation);
		}
		catch (Exception e)
		{
			throw Throwables.propagate(MoreThrowables.initializeSuppressor(e, cause));
		}
	}
	
	protected final Class<? extends Listener> parentLoadClass(final String name, @Nullable final Exception cause)
	{
		try
		{
			return this.parentOrFailure().loadClass(name);
		}
		catch (Exception e)
		{
			throw Throwables.propagate(MoreThrowables.initializeSuppressor(e, cause));
		}
	}
}
