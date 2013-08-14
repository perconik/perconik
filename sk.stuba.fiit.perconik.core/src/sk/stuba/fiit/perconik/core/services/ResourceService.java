package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Service;

public interface ResourceService extends Service
{
	public ResourceProvider getResourceProvider();
	
	public ResourceManager getResourceManager();
}
