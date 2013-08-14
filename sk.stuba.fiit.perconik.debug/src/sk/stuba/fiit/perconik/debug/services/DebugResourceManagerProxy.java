package sk.stuba.fiit.perconik.debug.services;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ResourceManager;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public class DebugResourceManagerProxy extends DebugObjectProxy<ResourceManager> implements DebugResourceManager
{
	public DebugResourceManagerProxy(final ResourceManager manager)
	{
		super(manager);
	}
	
	public DebugResourceManagerProxy(final ResourceManager manager, final PluginConsole console)
	{
		super(manager, console);
	}

	public final <L extends Listener> void register(final Class<L> type, final Resource<L> resource)
	{
		// TODO Auto-generated method stub
		
	}

	public final <L extends Listener> void unregister(final Class<L> type, final Resource<L> resource)
	{
		// TODO Auto-generated method stub
		
	}

	public final Set<Resource<?>> registered()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public final <L extends Listener> Set<Resource<? extends L>> assignable(final Class<L> type)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public final <L extends Listener> Set<Resource<? super L>> registerable(final Class<L> type)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
