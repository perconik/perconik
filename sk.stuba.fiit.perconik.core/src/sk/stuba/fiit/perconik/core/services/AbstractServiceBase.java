package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Manager;
import sk.stuba.fiit.perconik.core.Provider;
import com.google.common.base.Preconditions;

abstract class AbstractServiceBase<P extends Provider, M extends Manager> extends AbstractService
{
	final P provider;
	
	final M manager;
	
	AbstractServiceBase(final Builder<P, M> builder)
	{
		this.provider = Preconditions.checkNotNull(builder.provider);
		this.manager  = Preconditions.checkNotNull(builder.manager);
	}
	
	protected static abstract class Builder<P extends Provider, M extends Manager>
	{
		P provider;
		
		M manager;
		
		protected Builder()
		{
		}

		public final Builder<P, M> provider(final P provider)
		{
			Preconditions.checkState(this.provider == null);
			
			this.provider = Preconditions.checkNotNull(provider);
			
			return this;
		}
		
		public final Builder<P, M> manager(final M manager)
		{
			Preconditions.checkState(this.manager == null);
			
			this.manager = Preconditions.checkNotNull(manager);
			
			return this;
		}
		
		protected abstract AbstractServiceBase<P, M> build();
	}
}
