package sk.stuba.fiit.perconik.core.services.resources;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.services.ServiceFactory;

/**
 * The {@code ResourceServiceFactory}
 * creates {@link ResourceService} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ResourceServiceFactory extends ServiceFactory
{
	/**
	 * Creates a resource service.
	 */
	@Override
	public ResourceService create();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(@Nullable Object o);
}
