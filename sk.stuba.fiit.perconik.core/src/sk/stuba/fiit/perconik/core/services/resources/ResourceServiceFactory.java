package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.ServiceFactory;

public interface ResourceServiceFactory extends ServiceFactory
{
	@Override
	public ResourceService create();
}
