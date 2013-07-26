package sk.stuba.fiit.perconik.core.listeners;

import java.util.Map;
import sk.stuba.fiit.perconik.core.adapters.Adapter;
import com.google.common.util.concurrent.AtomicLongMap;

public abstract class AbstractCounter extends Adapter implements Counter
{
	protected final AtomicLongMap<Key> counts;
	
	protected AbstractCounter()
	{
		this.counts = AtomicLongMap.create();
	}
	
	public final Map<Key, Long> getCounts()
	{
		return this.counts.asMap();
	}

	public final long getCount(final Key key)
	{
		return this.counts.get(key);
	}
}
