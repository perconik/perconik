package sk.stuba.fiit.perconik.activity.data;

import java.util.Map;

import javax.annotation.Nullable;

public interface Content
{
	@Override
	public boolean equals(@Nullable Object o);

	@Override
	public int hashCode();

	public Map<String, Object> toMap();
	
	public Map<String, Object> toMap(boolean pretty);
	
	@Override
	public String toString();
	
	public String toString(boolean pretty);
}
