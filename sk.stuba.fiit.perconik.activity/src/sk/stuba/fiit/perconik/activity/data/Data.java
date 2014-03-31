package sk.stuba.fiit.perconik.activity.data;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Maps;

public class Data
{
	static final JavaType type = TypeFactory.defaultInstance().constructMapType(LinkedHashMap.class, String.class, Object.class);
	
	public Data()
	{
	}

	public static final <T extends Data> T fromMap(final Class<T> type, final Map<String, Object> data)
	{
		final Object carrier = new Object()
		{
			@JsonAnyGetter
			public final Map<String, Object> view()
			{
				return data;
			}
		};
		
		try
		{
			return fromString(type, Mapper.getInstance().writeValueAsString(carrier));
		}
		catch (JsonProcessingException e)
		{
			throw new DataException(e);
		}
	}

	public static final <T extends Data> T fromString(final Class<T> type, final String data)
	{
		try
		{
			return Mapper.getInstance().readValue(data, type);
		}
		catch (Exception e)
		{
			throw new DataException(e);
		}
	}

	@Override
	public final boolean equals(final Object o)
	{
		return o == this || o instanceof Data && ((Data) o).toMap().equals(this.toMap());
	}

	@Override
	public final int hashCode()
	{
		return this.toMap().hashCode();
	}

	public Map<String, Object> toMap()
	{
		return Mapper.getInstance().convertValue(this, type);
	}
	
	public Map<String, Object> toMap(boolean pretty)
	{
		if (!pretty)
		{
			return this.toMap();
		}
			
		Map<String, Object> map = Maps.newTreeMap();

		map.putAll(this.toMap());
		
		return map;
	}
	
	@Override
	public String toString()
	{
		try
		{
			return Mapper.getInstance().writeValueAsString(this);
		}
		catch (JsonProcessingException e)
		{
			throw new DataException(e);
		}
	}
	
	public String toString(boolean pretty)
	{
		if (!pretty)
		{
			return this.toString();
		}
		
		try
		{
			return Mapper.getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		}
		catch (JsonProcessingException e)
		{
			throw new DataException(e);
		}
	}
}
