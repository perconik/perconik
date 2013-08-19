package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.AbstractService;
import com.google.common.base.Preconditions;

public abstract class AbstractListenerService extends AbstractService implements ListenerService
{
	final ListenerProvider provider;
	
	final ListenerManager manager;
	
	protected AbstractListenerService(final ListenerProvider provider, final ListenerManager manager)
	{
		this.provider = Preconditions.checkNotNull(provider);
		this.manager  = Preconditions.checkNotNull(manager);
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
