package sk.stuba.fiit.perconik.core.services.listeners;

final class StandardListenerService extends AbstractListenerService
{
	StandardListenerService(final Builder builder)
	{
		super(builder);
	}
	
	public static final class Builder extends AbstractBuilder<Builder>
	{
		public Builder()
		{
		}

		@Override
		protected final Builder implementation()
		{
			return this;
		}

		@Override
		public final ListenerService build()
		{
			return new StandardListenerService(this);
		}
	}
	
	public static final Builder builder()
	{
		return new Builder();
	}
	
	@Override
	protected final void doStart()
	{
		this.notifyStarted();
	}

	@Override
	protected final void doStop()
	{
		this.manager.unregisterAll(sk.stuba.fiit.perconik.core.Listener.class);
		
		this.notifyStopped();
	}
}
