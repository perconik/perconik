package sk.stuba.fiit.perconik.core.services.listeners;

final class StandardListenerService extends AbstractListenerService
{
	StandardListenerService(final ListenerProvider provider, final ListenerManager manager)
	{
		super(provider, manager);
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
}
