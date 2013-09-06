package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.IllegalListenerClassException;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.AbstractProvider;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;
import com.google.common.base.Throwables;

public abstract class AbstractListenerProvider extends AbstractProvider implements ListenerProvider
{
	protected AbstractListenerProvider()
	{
	}

	protected abstract ClassLoader loader();
	
	// TODO refactor
	protected static final Class<? extends Listener> cast(final Class<?> type)
	{
		if (!Listener.class.isAssignableFrom(type))
		{
			throw new IllegalListenerClassException("Class " + type.getName() + " is not assignable to " + Listener.class.getName());
		}
		
		if (type.isInterface() || type.isAnnotation())
		{
			throw new IllegalListenerClassException("Type " + type.getName() + " can not be an interface or an annotation");
		}
		
		try
		{
			type.getConstructor();
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			throw new IllegalListenerClassException("Class " + type.getName() + " must have public constructor with no parameters", e);
		}
		
		return type.asSubclass(Listener.class);
	}

	protected final Class<? extends Listener> load(String name) throws ClassNotFoundException
	{
		return cast(this.loader().loadClass(name));
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

	protected final <L extends Listener> L parentForClass(final Class<L> type, final Exception cause)
	{
		try
		{
			return this.parentOrFailure().forClass(type);
		}
		catch (Exception e)
		{
			throw Throwables.propagate(MoreThrowables.initializeSuppressor(e, cause));
		}
	}
	
	protected final Class<? extends Listener> parentLoadClass(final String name, final Exception cause)
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
