package sk.stuba.fiit.perconik.core.listeners;

import sk.stuba.fiit.perconik.core.services.AbstractListenerService;

final class GenericListenerService extends AbstractListenerService
{
	GenericListenerService(final Builder builder)
	{
		super(builder);
	}
	
	public static final class Builder extends AbstractListenerService.Builder
	{
		public Builder()
		{
		}
		
		@Override
		public final GenericListenerService build()
		{
			return new GenericListenerService(this);
		}
	}
	
	public static final Builder builder()
	{
		return new Builder();
	}
}
