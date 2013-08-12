package sk.stuba.fiit.perconik.core.listeners;

import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.services.AbstractListenerService;
import sk.stuba.fiit.perconik.core.services.ResourceService;

final class GenericListenerService extends AbstractListenerService
{
	GenericListenerService()
	{
	}

	@Override
	protected final ResourceService service()
	{
		return Resources.getResourceService();
	}

	@Override
	public final String toString()
	{
		return this.getName();
	}

	public final String getName()
	{
		return this.getClass().getCanonicalName();
	}
}
