package sk.stuba.fiit.perconik.core.plugin;

import java.util.Set;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManagerFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerServiceFactory;
import sk.stuba.fiit.perconik.core.services.resources.ResourceNamesSupplier;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManagerFactory;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProviderFactory;
import sk.stuba.fiit.perconik.core.services.resources.ResourceServiceFactory;
import com.google.common.collect.ImmutableSet;

final class ExtensionPoints
{
	static final String resourcesPoint = Activator.PLUGIN_ID + ".services.resources";

	static final Set<Class<?>> resourcesTypes;

	static final String listenersPoint = Activator.PLUGIN_ID + ".services.listeners";

	static final Set<Class<?>> listenersTypes; 
	
	static
	{
		resourcesTypes = ImmutableSet.of
		(
			ResourceProviderFactory.class,
			ResourceManagerFactory.class,
			ResourceServiceFactory.class,
			ResourceNamesSupplier.class
		);

		listenersTypes = ImmutableSet.of
		(
			ListenerProviderFactory.class,
			ListenerManagerFactory.class,
			ListenerServiceFactory.class,
			ListenerClassesSupplier.class
		);
	}
	
	private ExtensionPoints()
	{
		throw new AssertionError();
	}
}
