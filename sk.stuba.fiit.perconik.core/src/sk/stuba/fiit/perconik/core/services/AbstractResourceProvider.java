package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.collect.BiMap;

public abstract class AbstractResourceProvider extends AbstractProvider implements ResourceProvider
{
	protected AbstractResourceProvider()
	{
	}
	
	protected abstract BiMap<String, Resource<?>> map();
	
	public final Resource<?> forName(final String name)
	{
		return this.map().get(name);
	}
}
