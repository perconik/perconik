package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.AbstractProvider;
import com.google.common.collect.BiMap;

public abstract class AbstractListenerProvider extends AbstractProvider implements ListenerProvider
{
	protected AbstractListenerProvider()
	{
	}
	
	protected abstract BiMap<String, Class<? extends Listener>> map();

	protected abstract Class<? extends Listener> load(String name) throws ClassNotFoundException;
	
	protected abstract void validate(Class<? extends Listener> type);
	
	public final <L extends Listener> L forClass(Class<L> type)
	{
		try
		{
			if (!this.map().containsValue(type))
			{
				this.validate(type);
				this.map().put(type.getName(), type);
			}
			
			return type.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new UnsupportedOperationException(e);
		}
	}

	public final Class<? extends Listener> loadClass(final String name) throws ClassNotFoundException
	{
		Class<? extends Listener> type = this.map().get(name);
		
		if (type == null)
		{
			type = this.load(name);
			
			this.validate(type);
			this.map().put(name, type);
		}
		
		return type;
	}
}
