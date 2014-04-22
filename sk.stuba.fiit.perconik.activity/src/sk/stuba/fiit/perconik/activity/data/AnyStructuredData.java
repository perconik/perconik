package sk.stuba.fiit.perconik.activity.data;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static sk.stuba.fiit.perconik.utilities.MoreThrowables.initializeCause;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.activity.data.bind.Deserializer;
import sk.stuba.fiit.perconik.utilities.MoreMaps;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class AnyStructuredData extends AnyData implements AnyStructuredContent
{
	public static final String separator = ".";
	
	public AnyStructuredData()
	{
		super(new Structure());
	}
	
	protected AnyStructuredData(Map<String, Object> other)
	{
		super(Structure.from(other));
	}
	
	public static AnyStructuredData fromMap(Map<String, Object> data)
	{
		return fromMap(AnyStructuredData.class, data);
	}

	public static AnyStructuredData fromString(String data)
	{
		return fromString(AnyStructuredData.class, data);
	}

	public static AnyStructuredData of(Map<String, Object> other)
	{
		return new AnyStructuredData(other);
	}
	
	static final class Structure extends ForwardingMap<String, Object> implements Serializable
	{
		private static final long serialVersionUID = -8851372460060747540L;

		static final Joiner joiner = Joiner.on(separator).skipNulls();

		static final Splitter splitter = Splitter.on(separator).trimResults();

		final transient Map<String, Object> map;
		
		Structure()
		{
			this.map = Maps.newLinkedHashMap();
		}
		
		static final Structure from(final Map<String, Object> map)
		{
			Structure structure = new Structure();
			
			structure.putAll(map);
			
			return structure;
		}
		
		@Override
		protected final Map<String, Object> delegate()
		{
			return this.map;
		}
		
		// TODO rm
//		private final Map<String, Object> flatten(Map<String, Object> flat, String prefix)
//		{
//			for (Entry<String, Object> entry: this.map.entrySet())
//			{
//				String key   = joiner.join(prefix, entry.getKey());
//				Object value = entry.getValue();
//				
//				if (value instanceof AnyStructuredData)
//				{
//					((AnyStructuredData) value).structure().flatten(flat, key);
//				}
//				else
//				{
//					flat.put(key, value);
//				}
//			}
//			
//			return flat;
//		}
//
//		final Map<String, Object> flatten()
//		{
//			return this.flatten(Maps.<String, Object>newLinkedHashMap(), null);
//		}

		private static final Iterator<String> normalize(Iterator<String> key)
		{
			checkState(key.hasNext());

			String component = key.next();

			checkArgument(!component.isEmpty());
			
			Iterator<String> components = splitter.split(component).iterator();
			
			return key.hasNext() ? Iterators.concat(components, key) : components;
		}
		
		final Object put(Iterator<String> key, Object value)
		{
			key = normalize(key);
			
			String component = key.next();
			
			if (!key.hasNext())
			{
				return this.map.put(component, value);
			}

			AnyStructuredData data;
			
			Object other = this.map.get(component);
			
			if (other instanceof AnyStructuredData)
			{
				data = (AnyStructuredData) other;
			}
			else
			{
				data = new AnyStructuredData();
				
				this.map.put(component, data);
			}
			
			return data.structure().put(key, value);
		}

		final Object remove(Iterator<String> key)
		{
			key = normalize(key);
			
			String component = key.next();
			
			if (!key.hasNext())
			{
				return this.map.remove(component);
			}
			
			Object value = this.map.get(component);
			
			if (value instanceof AnyStructuredData)
			{
				return ((AnyStructuredData) value).structure().remove(key);
			}
			
			return null;
		}
		
		final boolean contains(Iterator<String> key)
		{
			key = normalize(key);
			
			String component = key.next();

			if (!key.hasNext())
			{
				return this.map.containsKey(component);
			}
			
			Object value = this.map.get(component);
			
			if (value instanceof AnyStructuredData)
			{
				return ((AnyStructuredData) value).structure().contains(key);
			}
			
			return false;
		}
		
		final Object get(Iterator<String> key)
		{
			key = normalize(key);
			
			Object value = this.map.get(key.next());

			if (!key.hasNext())
			{
				return value;
			}
			
			if (value instanceof AnyStructuredData)
			{
				return ((AnyStructuredData) value).structure().get(key);
			}
			
			return null;
		}
		
		@Override
		public final Object put(final String key, final Object value)
		{
			return this.put(Iterators.singletonIterator(key), value);
		}

		@Override
		public final void putAll(Map<? extends String, ? extends Object> map)
		{
			this.standardPutAll(map);
		}

		@Override
		public final Object remove(final Object key)
		{
			return this.remove(Iterators.singletonIterator((String) key));
		}

		@Override
		public final boolean containsKey(final Object key)
		{
			return this.contains(Iterators.singletonIterator((String) key));
		}

		@Override
		public final Object get(final Object key)
		{
			if (key instanceof String)
			{
				return this.get(Iterators.singletonIterator((String) key));
			}
			
			return null;
		}

		private static class SerializationProxy<E extends Enum<E>> implements Serializable
		{
			private static final long serialVersionUID = 7166925961646497798L;

			private final Map<String, Object> map;
			
			SerializationProxy(final Structure structure)
			{
				this.map = structure.map;
			}

			private final Object readResolve() throws InvalidObjectException
			{
				try
				{
					return from(this.map);
				}
				catch (Exception e)
				{
					throw initializeCause(new InvalidObjectException("Unknown deserialization error"), e);
				}
			}
		}

		@SuppressWarnings({"static-method", "unused"})
		private final void readObject(final ObjectInputStream in) throws InvalidObjectException
		{
			throw new InvalidObjectException("Serialization proxy required");
		}

		private final Object writeReplace()
		{
			return new SerializationProxy<>(this);
		}
	}
	
	final Structure structure()
	{
		return (Structure) this.other;
	}
	
	public Map<String, Object> flatten()
	{
		return MoreMaps.flatten(this.toMap(), Structure.joiner, Maps.<String, Object>newLinkedHashMap());
	}

	@JsonAnySetter
	@JsonDeserialize(using = Deserializer.class)
	@Override
	public void set(String key, @Nullable Object value)
	{
		this.other.put(key, value);
	}

	public void set(Iterable<String> key, Object value)
	{
		this.set(key.iterator(), value);
	}

	public void set(Iterator<String> key, Object value)
	{
		this.structure().put(key, value);
	}

	@Override
	public Object get(String key)
	{
		return this.other.get(key);
	}

	public Object get(String key, String ... more)
	{
		return this.get(Lists.asList(key, more));
	}

	public Object get(Iterable<String> key)
	{
		return this.get(key.iterator());
	}

	public Object get(Iterator<String> key)
	{
		return this.structure().get(key);
	}
}
