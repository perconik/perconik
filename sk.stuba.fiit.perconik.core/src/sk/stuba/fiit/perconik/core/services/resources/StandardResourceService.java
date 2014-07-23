package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.ResourceUnregistrationException;

import static sk.stuba.fiit.perconik.core.utilities.LogHelper.log;

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
		try
		{
			this.manager.unregisterAll(sk.stuba.fiit.perconik.core.Listener.class);
		}
		catch (ResourceUnregistrationException failure)
		{
			log.error(failure, "Unexpected error during final unregistration of resources");
		}
	
		this.notifyStopped();
	}
}
