package sk.stuba.fiit.perconik.debug.services;

import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ResourceProvider;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public class DebugResourceProviderProxy extends DebugObjectProxy<ResourceProvider> implements DebugResourceProvider
{
	private DebugResourceProviderProxy(final ResourceProvider provider, final PluginConsole console)
	{
		super(provider, console);
	}

	public static final DebugResourceProviderProxy of(final ResourceProvider provider)
	{
		return of(provider, Debug.getDefaultConsole());
	}

	public static final DebugResourceProviderProxy of(final ResourceProvider provider, final PluginConsole console)
	{
		if (provider instanceof DebugResourceProviderProxy)
		{
			return (DebugResourceProviderProxy) provider;
		}
		
		return new DebugResourceProviderProxy(provider, console);
	}

	public final Resource<?> forName(final String name)
	{
		this.put("Requesting resource by name ", name, " ... ");
		
		Resource<?> resource = this.delegate().forName(name);
		
		this.print(resource != null ? "done (" + DebugResources.toString(resource) + ")" : "failed");
		
		return resource;
	}

	public final Iterable<Resource<?>> resources()
	{
		return this.delegate().resources();
	}
}
