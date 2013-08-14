package sk.stuba.fiit.perconik.debug.services;

import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ResourceProvider;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public class DebugResourceProviderProxy extends DebugObjectProxy<ResourceProvider> implements DebugResourceProvider
{
	public DebugResourceProviderProxy(final ResourceProvider provider)
	{
		super(provider);
	}
	
	public DebugResourceProviderProxy(final ResourceProvider provider, final PluginConsole console)
	{
		super(provider, console);
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
