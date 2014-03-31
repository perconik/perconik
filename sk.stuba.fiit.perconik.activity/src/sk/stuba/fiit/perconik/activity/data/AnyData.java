package sk.stuba.fiit.perconik.activity.data;

import java.util.Map;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.common.collect.Maps;

public class AnyData extends Data
{
	protected Map<String, Object> other;
	
	public AnyData()
	{
		this.other = Maps.newLinkedHashMap();
	}

	@JsonAnyGetter
	public final Map<String, Object> any()
	{
		return this.other;
	}

	@JsonAnySetter
	public final void set(final String key, @Nullable final Object value)
	{
		this.other.put(key, value);
	}

	public final Object get(final String key)
	{
		return this.other.get(key);
	}
}
