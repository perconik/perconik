package sk.stuba.fiit.perconik.core.services;


public abstract class AbstractListenerService extends AbstractService<ListenerProvider, ListenerManager> implements ListenerService
{
	protected AbstractListenerService(final Builder builder)
	{
		super(builder);
	}
	
	protected static abstract class Builder extends AbstractService.Builder<ListenerProvider, ListenerManager>
	{
		protected Builder()
		{
		}

		@Override
		protected abstract AbstractListenerService build();
	}

	public final ListenerProvider getListenerProvider()
	{
		return this.provider;
	}

	public final ListenerManager getListenerManager()
	{
		return this.manager;
	}
}
