package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.ProviderFactory;

public interface ListenerProviderFactory extends ProviderFactory<ListenerProvider>
{
	@Override
	public ListenerProvider create(ListenerProvider parent);
}
