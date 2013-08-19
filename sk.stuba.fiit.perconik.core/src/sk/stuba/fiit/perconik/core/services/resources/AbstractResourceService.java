package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.AbstractService;
import com.google.common.base.Preconditions;

public abstract class AbstractResourceService extends AbstractService implements ResourceService
{
	final ResourceProvider provider;
	
	final ResourceManager manager;
	
	protected AbstractResourceService(final ResourceProvider provider, final ResourceManager manager)
	{
		this.provider = Preconditions.checkNotNull(provider);
		this.manager  = Preconditions.checkNotNull(manager);
	}
	
	public final ResourceProvider getResourceProvider()
	{
		this.checkRunning();
		
		return this.provider;
	}

	public final ResourceManager getResourceManager()
	{
		this.checkRunning();
		
		return this.manager;
	}
}
