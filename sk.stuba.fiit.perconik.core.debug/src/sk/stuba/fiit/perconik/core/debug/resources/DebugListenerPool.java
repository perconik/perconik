package sk.stuba.fiit.perconik.core.debug.resources;

import java.util.Collection;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.debug.AbstractDebugResource;
import sk.stuba.fiit.perconik.core.debug.DebugListener;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugImplementation;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@DebugImplementation
public final class DebugListenerPool extends AbstractDebugResource<DebugListener>
{
	private static final DebugListenerPool instance = new DebugListenerPool();
	
	private final Set<DebugListener> pool = Sets.newConcurrentHashSet();
	
	private DebugListenerPool()
	{
	}
	
	public static final DebugListenerPool getInstance()
	{
		return instance;
	}
	
	public final void register(final DebugListener listener)
	{
		this.pool.add(listener);
	}

	public final void unregister(final DebugListener listener)
	{
		this.pool.remove(listener);
	}

	public final <U extends Listener> Collection<U> registered(final Class<U> type)
	{
		Collection<U> result = Lists.newArrayList();
		
		for (DebugListener listener: this.pool)
		{
			if (type.isInstance(listener))
			{
				result.add(type.cast(listener));
			}
		}
		
		return result;
	}

	public final boolean registered(Listener listener)
	{
		return this.pool.contains(listener);
	}
}
