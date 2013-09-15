package sk.stuba.fiit.perconik.utilities;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;
import com.google.common.collect.Maps;

/**
 * Static utility methods pertaining to {@code Map} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreMaps
{
	private MoreMaps()
	{
		throw new AssertionError();
	}
	
	private static final <K, V> void copy(Dictionary<? extends K, ? extends V> from, Map<K, V> to)
	{
		Enumeration<? extends K> keys = from.keys();
		
		while (keys.hasMoreElements())
		{
			K key = keys.nextElement();
			
			to.put(key, from.get(key));
		}
	}
	
	public static final <K, V> Map<K, V> fromDictionary(Dictionary<K, V> dictionary)
	{
		Map<K, V> map = Maps.newHashMapWithExpectedSize(dictionary.size());
		
		copy(dictionary, map);
		
		return map;
	}
}
