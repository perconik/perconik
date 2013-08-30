package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.ManagerFactory;

public interface ResourceManagerFactory extends ManagerFactory
{
	@Override
	public ResourceManager create();
}
