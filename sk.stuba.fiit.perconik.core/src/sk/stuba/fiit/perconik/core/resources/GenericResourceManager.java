package sk.stuba.fiit.perconik.core.resources;

import java.util.Map.Entry;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.AbstractResourceManager;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

final class GenericResourceManager extends AbstractResourceManager
{
	private final SetMultimap<Class<? extends Listener>, Resource<?>> storage;
	
	GenericResourceManager()
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
}
