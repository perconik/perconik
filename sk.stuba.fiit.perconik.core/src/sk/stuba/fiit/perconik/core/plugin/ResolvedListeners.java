package sk.stuba.fiit.perconik.core.plugin;

import com.google.common.base.Preconditions;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;

final class ResolvedListeners extends ResolvedService<ListenerService>
{
	final ListenerClassesSupplier supplier;

	ResolvedListeners(final ListenerService service, final ListenerClassesSupplier supplier)
	{
		super(service);
		
		this.supplier = Preconditions.checkNotNull(supplier);
	}
}
