package sk.stuba.fiit.perconik.utilities;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.base.Joiner;
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
	
	public static final Map<String, Object> flatten(Map<?, Object> map)
	{
		return flatten(map, Joiner.on("."));
	}
	
	public static final Map<String, Object> flatten(Map<?, Object> map, Joiner joiner)
	{
		return flatten(map, joiner, Maps.<String, Object>newLinkedHashMap());
	}

	public static final Map<String, Object> flatten(Map<?, Object> map, Joiner joiner, Map<String, Object> result)
	{
		return flatten(map, joiner, result, (String) null);
	}

	@SuppressWarnings("unchecked")
	private static final Map<String, Object> flatten(Map<?, Object> map, Joiner joiner, Map<String, Object> result, String prefix)
	{
		for (Entry<?, Object> entry: map.entrySet())
		{
			String key   = joiner.join(prefix, entry.getKey());
			Object value = entry.getValue();
			
			if (value instanceof Map)
			{
				flatten(((Map<?, Object>) value), joiner, result, key);
			}
			else
			{
				result.put(key, value);
			}
		}
		
		return result;
	}
}
