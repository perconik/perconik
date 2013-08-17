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

	@Override
	protected final void doStart()
	{
		for (Class<? extends sk.stuba.fiit.perconik.core.Listener> type: this.provider.classes())
		{
			this.registerListenerForClass(type);
		}
		
		this.notifyStarted();
	}

	@Override
	protected final void doStop()
	{
		this.manager.unregisterAll(sk.stuba.fiit.perconik.core.Listener.class);
		
		this.notifyStopped();
	}

	private final <L extends sk.stuba.fiit.perconik.core.Listener> void registerListenerForClass(final Class<L> type)
	{
		L listener = this.provider.forClass(type);
		
		this.manager.register(listener);
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
