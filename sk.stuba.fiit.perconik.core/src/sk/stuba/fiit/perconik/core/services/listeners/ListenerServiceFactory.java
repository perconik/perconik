package sk.stuba.fiit.perconik.core.services.listeners;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.services.ServiceFactory;

/**
 * The {@code ListenerServiceFactory}
 * creates {@link ListenerService} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ListenerServiceFactory extends ServiceFactory
{
	/**
	 * Creates a listener service.
	 */
	@Override
	public ListenerService create();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(@Nullable Object o);
}
