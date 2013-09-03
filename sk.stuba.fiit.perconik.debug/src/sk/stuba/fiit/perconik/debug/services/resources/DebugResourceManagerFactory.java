package sk.stuba.fiit.perconik.debug.services.resources;

import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManagerFactory;

public final class DebugResourceManagerFactory implements ResourceManagerFactory
{
	public DebugResourceManagerFactory()
	{
	}

	public final ResourceManager create()
	{
		return DebugResourceManagers.create();
	}
}
