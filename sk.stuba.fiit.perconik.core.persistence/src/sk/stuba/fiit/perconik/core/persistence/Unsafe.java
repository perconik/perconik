package sk.stuba.fiit.perconik.core.persistence;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

final class Unsafe
{
	private Unsafe()
	{
		throw new AssertionError();
	}
	
	static final <L extends Listener> Resource<L> cast(final Class<L> type, final Resource<?> resource)
	{
		@SuppressWarnings("serial")
		TypeToken<Resource<L>> token = new TypeToken<Resource<L>>(){}.where(new TypeParameter<L>(){}, TypeToken.of(type));
		
		return (Resource<L>) token.getRawType().cast(resource);
	}

	static final <L extends Listener> void register(final Class<L> type, final Resource<?> resource)
	{
		Resources.register(type, cast(type, resource));
	}
	
	static final <L extends Listener> void unregister(final Class<L> type, final Resource<?> resource)
	{
		Resources.unregister(type, cast(type, resource));
	}
}
