package sk.stuba.fiit.perconik.core;

import java.util.Map;
import com.google.common.util.concurrent.AtomicLongMap;

/**
 * An abstract implementation of {@link CountingListener}
 * holding event counts in an atomic map.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractCountingListener extends Adapter implements CountingListener
{
	/**
	 * Atomic map of keys to event counts.
	 */
	protected final AtomicLongMap<Key> counts;
	
	/**
	 * Constructor for use by subclasses.
	 */
	protected AbstractCountingListener()
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
