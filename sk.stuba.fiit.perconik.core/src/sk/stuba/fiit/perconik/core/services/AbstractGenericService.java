package sk.stuba.fiit.perconik.core.services;

import com.google.common.base.Preconditions;

public abstract class AbstractGenericService<P extends Provider, M extends Manager> extends AbstractService
{
	protected final P provider;
	
	protected final M manager;
	
	protected AbstractGenericService(final AbstractGenericBuilder<?, P, M> builder)
	{
		this.provider    = Preconditions.checkNotNull(builder.provider);
		this.manager     = Preconditions.checkNotNull(builder.manager);
	}
	
	protected static abstract class AbstractGenericBuilder<B extends AbstractGenericBuilder<B, P, M>, P extends Provider, M extends Manager>
	{
		P provider;
		
		M manager;
		
		protected AbstractGenericBuilder()
		{
		}
		
		protected abstract B implementation();
		
		public final B provider(final P provider)
		{
			Preconditions.checkState(this.provider == null);
			
			this.provider = Preconditions.checkNotNull(provider);
			
			return this.implementation();
		}
		
		public final B manager(final M manager)
		{
			Preconditions.checkState(this.manager == null);
			
			this.manager = Preconditions.checkNotNull(manager);
			
			return this.implementation();
		}
		
		public abstract Service build();
	}
}
