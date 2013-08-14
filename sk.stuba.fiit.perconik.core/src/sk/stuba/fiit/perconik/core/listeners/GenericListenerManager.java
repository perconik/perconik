package sk.stuba.fiit.perconik.core.listeners;

import sk.stuba.fiit.perconik.core.Services;
import sk.stuba.fiit.perconik.core.services.AbstractListenerManager;
import sk.stuba.fiit.perconik.core.services.ResourceManager;

final class GenericListenerManager extends AbstractListenerManager
{
	GenericListenerManager()
	{
	}

	@Override
	protected final ResourceManager manager()
	{
		return Services.getResourceService().getResourceManager();
	}
}
