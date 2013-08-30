package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.AbstractGenericService;

public abstract class AbstractListenerService extends AbstractGenericService<ListenerProvider, ListenerManager> implements ListenerService
{
	protected AbstractListenerService(final AbstractBuilder<?> builder)
	{
		super(builder);
	}
	
	protected static abstract class AbstractBuilder<B extends AbstractBuilder<B>> extends AbstractGenericBuilder<B, ListenerProvider, ListenerManager> implements Builder
	{
		@Override
		public abstract ListenerService build();
	}
	
	public final ListenerProvider getListenerProvider()
	{
		this.checkRunning();
		
		return this.provider;
	}

	public final ListenerManager getListenerManager()
	{
		this.checkRunning();
		
		return this.manager;
	}
}
