package sk.stuba.fiit.perconik.core.resources;

import java.util.Map.Entry;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.AbstractResourceService;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

final class GenericResourceService extends AbstractResourceService
{
	private final SetMultimap<Class<? extends Listener>, Resource<?>> storage;
	
	GenericResourceService()
	{
		this.storage = Multimaps.synchronizedSetMultimap(HashMultimap.<Class<? extends Listener>, Resource<?>>create());
	}
	
	@Override
	protected final <L extends Listener> boolean put(Class<L> type, Resource<L> resource)
	{
		return this.storage.put(type, resource);
	}

	@Override
	protected final <L extends Listener> boolean remove(Class<L> type, Resource<L> resource)
	{
		return this.storage.remove(type, resource);
	}

	@Override
	protected final Set<Entry<Class<? extends Listener>, Resource<?>>> entries()
	{
		return this.storage.entries();
	}

	public final Set<Resource<?>> registered()
	{
		return Sets.newHashSet(this.storage.values());
	}

	@Override
	public final String toString()
	{
		return this.getName();
	}

	public final String getName()
	{
		return this.getClass().getCanonicalName();
	}
}
