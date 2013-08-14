package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.services.AbstractResourceService;

final class GenericResourceService extends AbstractResourceService
{
	GenericResourceService(final Builder builder)
	{
		super(builder);
	}
	
	public static final class Builder extends AbstractResourceService.Builder
	{
		public Builder()
		{
		}
		
		@Override
		public final GenericResourceService build()
		{
			return new GenericResourceService(this);
		}
	}
	
	public static final Builder builder()
	{
		return new Builder();
	}
}
