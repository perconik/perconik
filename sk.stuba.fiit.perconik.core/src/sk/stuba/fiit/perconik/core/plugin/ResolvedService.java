package sk.stuba.fiit.perconik.core.plugin;

import com.google.common.base.Preconditions;

import sk.stuba.fiit.perconik.core.services.Service;

abstract class ResolvedService<S extends Service>
{
	final S service;
	
	ResolvedService(final S service)
	{
		this.service = Preconditions.checkNotNull(service);
	}
}
