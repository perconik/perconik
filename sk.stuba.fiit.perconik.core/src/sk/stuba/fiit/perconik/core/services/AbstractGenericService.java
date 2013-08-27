package sk.stuba.fiit.perconik.core.services;

import com.google.common.base.Preconditions;

public abstract class AbstractGenericService<P extends Provider, M extends Manager, I extends Initializer> extends AbstractService
{
	protected final P provider;
	
	protected final M manager;
	
	protected final I initializer;
	
	protected AbstractGenericService(final AbstractGenericBuilder<?, P, M, I> builder)
	{
		this.provider    = Preconditions.checkNotNull(builder.provider);
		this.manager     = Preconditions.checkNotNull(builder.manager);
		this.initializer = Preconditions.checkNotNull(builder.initializer);
	}
	
	protected static abstract class AbstractGenericBuilder<B extends AbstractGenericBuilder<B, P, M, I>, P extends Provider, M extends Manager, I extends Initializer>
	{
		P provider;
		
		M manager;
		
		I initializer;
		
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
		
		public final B initializer(final I initializer)
		{
			Preconditions.checkState(this.initializer == null);
			
			this.initializer = Preconditions.checkNotNull(initializer);
			
			return this.implementation();
		}
		
		public abstract Service build();
	}
}
