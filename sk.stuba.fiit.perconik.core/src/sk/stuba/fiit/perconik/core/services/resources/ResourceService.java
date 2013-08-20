package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.Service;

public interface ResourceService extends Service
{
	public ResourceProvider getResourceProvider();
	
	public ResourceManager getResourceManager();
}
