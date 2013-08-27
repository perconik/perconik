package sk.stuba.fiit.perconik.core.services.resources;

final class StandardResourceService extends AbstractResourceService
{
	StandardResourceService(final Builder builder)
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
		public final ResourceService build()
		{
			return new StandardResourceService(this);
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
