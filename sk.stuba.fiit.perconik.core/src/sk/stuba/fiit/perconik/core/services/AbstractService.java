package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Service;
import com.google.common.base.Preconditions;

abstract class AbstractService<P, M> extends com.google.common.util.concurrent.AbstractService implements Service
{
	final P provider;
	
	final M manager;
	
	AbstractService(final Builder<P, M> builder)
	{
		this.provider = Preconditions.checkNotNull(builder.provider);
		this.manager  = Preconditions.checkNotNull(builder.manager);
	}
	
	protected static abstract class Builder<P, M>
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
		
		protected abstract AbstractService<P, M> build();
	}
	
	@Override
	public final String toString()
	{
		return this.getName() + " [" + this.state().toString().toLowerCase() + "]";
	}

	public final String getName()
	{
		return this.getClass().getName();
	}
}
