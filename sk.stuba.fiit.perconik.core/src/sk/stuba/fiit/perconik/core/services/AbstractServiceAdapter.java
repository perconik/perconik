package sk.stuba.fiit.perconik.core.services;

import com.google.common.base.Preconditions;

public abstract class AbstractServiceAdapter<S extends Service> extends ServiceAdapter implements ServiceListener 
{
	private final S service;
	
	protected AbstractServiceAdapter(final S service)
	{
		this.service = Preconditions.checkNotNull(service);
	}
	
	@Override
	public final S service()
	{
		return this.service;
	}
}
