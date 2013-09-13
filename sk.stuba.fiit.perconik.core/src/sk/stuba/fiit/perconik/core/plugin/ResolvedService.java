package sk.stuba.fiit.perconik.core.plugin;

import sk.stuba.fiit.perconik.core.services.Service;
import com.google.common.base.Preconditions;

abstract class ResolvedService<S extends Service>
{
	final S service;
	
	ResolvedService(final S service)
	{
		this.service = Preconditions.checkNotNull(service);
	}
}
