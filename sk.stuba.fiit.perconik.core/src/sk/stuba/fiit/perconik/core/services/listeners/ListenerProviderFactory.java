package sk.stuba.fiit.perconik.core.services.listeners;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.services.ProviderFactory;

/**
 * The {@code ListenerProviderFactory}
 * creates {@link ListenerProvider} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ListenerProviderFactory extends ProviderFactory<ListenerProvider>
{
	/**
	 * Creates a listener provider.
	 */
	@Override
	public ListenerProvider create(@Nullable ListenerProvider parent);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(@Nullable Object o);
}
