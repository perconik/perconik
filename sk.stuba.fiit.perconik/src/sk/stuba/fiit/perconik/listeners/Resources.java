package sk.stuba.fiit.perconik.listeners;

import com.google.common.reflect.ImmutableTypeToInstanceMap;
import com.google.common.reflect.ImmutableTypeToInstanceMap.Builder;
import com.google.common.reflect.TypeToInstanceMap;
import com.google.common.reflect.TypeToken;

@SuppressWarnings("serial")
public final class Resources
{
	private static final TypeToken<Resource<LaunchListener>> launch = new TypeToken<Resource<LaunchListener>>() {};
	
	private static final TypeToInstanceMap<Resource<?>> resources;
	
	static
	{
		Builder<Resource<?>> builder = ImmutableTypeToInstanceMap.builder();
		
		builder.put(launch, create(LaunchHandler.INSTANCE));
		
		resources = builder.build();
	}
	
	private Resources()
	{
		throw new AssertionError();
	}
	
	static final <T extends Listener> Resource<T> create(final Handler<T> handler)
	{
		return new GenericResource<>(Pools.getPoolFactory().create(handler));
	}
	
	public static final Resource<LaunchListener> getLaunchResource()
	{
		return (Resource<LaunchListener>) resources.get(launch);
	}
	
	
}
