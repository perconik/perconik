package sk.stuba.fiit.perconik.core.listeners;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.AbstractListenerProvider;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

final class GenericListenerProvider extends AbstractListenerProvider
{
	private final BiMap<String, Class<? extends Listener>> map;
	
	GenericListenerProvider()
	{
		this.map = HashBiMap.create();
	}
	
	@Override
	protected final BiMap<String, Class<? extends Listener>> map()
	{
		return this.map;
	}

	@Override
	protected final Class<? extends Listener> loadClassInternal(String name) throws ClassNotFoundException
	{
		Class<?> c = ClassLoader.getSystemClassLoader().loadClass(name);
		
		if (!Listener.class.isAssignableFrom(c))
		{
			throw new ClassCastException("Class " + c + " is not assignable to " + Listener.class);
		}
		
		@SuppressWarnings("unchecked")
		Class<? extends Listener> type = (Class<? extends Listener>) c;
		
		return type;
	}
	
	@Override
	protected final void validateClassInternal(final Class<? extends Listener> type)
	{
		if (type.isInterface() || type.isAnnotation() || type.isEnum())
		{
			throw new IllegalStateException("Type " + type + " can not be an interface or an enum");
		}

		try
		{
			type.getConstructor();
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			throw new IllegalStateException("Class " + type + " must have public constructor with no parameters");
		}
	}
}
