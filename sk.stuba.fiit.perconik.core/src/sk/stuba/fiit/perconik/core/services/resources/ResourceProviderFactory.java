package sk.stuba.fiit.perconik.core.services.resources;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.services.ProviderFactory;

/**
 * The {@code ResourceProviderFactory}
 * creates {@link ResourceProvider} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ResourceProviderFactory extends ProviderFactory<ResourceProvider>
{
	/**
	 * Creates a resource provider.
	 */
	@Override
	public ResourceProvider create(@Nullable ResourceProvider parent);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(@Nullable Object o);
}
