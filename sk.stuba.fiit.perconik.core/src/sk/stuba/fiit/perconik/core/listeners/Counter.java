package sk.stuba.fiit.perconik.core.listeners;

import java.util.Map;

public interface Counter extends Listener
{
	public Map<Key, Long> getCounts();

	public long getCount(Key key);
	
	public static interface Key
	{
	}
}
