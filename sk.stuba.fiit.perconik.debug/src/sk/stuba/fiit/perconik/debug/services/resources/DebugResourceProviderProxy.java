package sk.stuba.fiit.perconik.debug.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;
import sk.stuba.fiit.perconik.debug.DebugNameableProxy;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.debug.resources.DebugResourceProxy;
import com.google.common.collect.Sets;

public class DebugResourceProviderProxy extends DebugNameableProxy<ResourceProvider> implements DebugResourceProvider
{
	private DebugResourceProviderProxy(final ResourceProvider provider, final DebugConsole console)
	{
		super(provider, console);
	}

	public static final DebugResourceProviderProxy wrap(final ResourceProvider provider)
	{
		return wrap(provider, Debug.getDefaultConsole());
	}

	public static final DebugResourceProviderProxy wrap(final ResourceProvider provider, final DebugConsole console)
	{
		if (provider instanceof DebugResourceProviderProxy)
		{
			return (DebugResourceProviderProxy) provider;
		}
		
		return new DebugResourceProviderProxy(provider, console);
	}

	public static final ResourceProvider unwrap(final ResourceProvider provider)
	{
		if (provider instanceof DebugResourceProviderProxy)
		{
			return ((DebugResourceProviderProxy) provider).delegate();
		}
		
		return provider;
	}
	
	private static final Set<Resource<?>> wrap(final Iterable<Resource<?>> resources)
	{
		Set<Resource<?>> proxies = Sets.newHashSet();
		
		for (Resource<?> resource: resources)
		{
			proxies.add(DebugResourceProxy.wrap(resource));
		}
		
		return proxies;
	}

	private static final <L extends Listener> Set<Resource<? super L>> wrap(final Set<Resource<? super L>> resources)
	{
		Set<Resource<? super L>> proxies = Sets.newHashSetWithExpectedSize(resources.size());
		
		for (Resource<? super L> resource: resources)
		{
			proxies.add(DebugResourceProxy.wrap(resource));
		}
		
		return proxies;
	}

	public final Resource<?> forName(final String name)
	{
		this.put("Requesting resource by name %s ... ", name);
		
		Resource<?> resource = this.delegate().forName(name);
		
		this.print(resource != null ? "done (" + DebugResources.toString(resource) + ")" : "failed");
		
		return DebugResourceProxy.wrap(resource);
	}

	public final <L extends Listener> Set<Resource<? super L>> forClass(final Class<L> type)
	{
		this.put("Requesting resources for %s ... ", type.getName());
		
		Set<Resource<? super L>> resources = this.delegate().forClass(type);
		
		this.print(!resources.isEmpty() ? "done" : "failed");
		
		return wrap(resources);
	}

	public final Iterable<String> names()
	{
		return this.delegate().names();
	}

	public final Iterable<Class<? extends Listener>> classes()
	{
		return this.delegate().classes();
	}

	public final Iterable<Resource<?>> resources()
	{
		return wrap(this.delegate().resources());
	}
}
