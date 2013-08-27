package sk.stuba.fiit.perconik.preferences.plugin;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.services.Service;
import sk.stuba.fiit.perconik.core.services.ServiceGroup;
import sk.stuba.fiit.perconik.core.services.ServiceSnapshot;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerInitializer;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerServices;
import sk.stuba.fiit.perconik.core.services.resources.ResourceInitializer;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceServices;
import sk.stuba.fiit.perconik.preferences.ListenerPreferences;
import sk.stuba.fiit.perconik.preferences.ResourcePreferences;
import sk.stuba.fiit.perconik.preferences.persistence.Registrations;
import com.google.common.base.Preconditions;

final class PreferencesLoader
{
	private final Loader loader;
	
	private final Unloader unloader;
	
	private PreferencesLoader()
	{
		ServiceSnapshot snapshot = ServiceSnapshot.take();
		
		this.loader   = new Loader(snapshot);
		this.unloader = new Unloader(snapshot);
	}
	
	static final PreferencesLoader create()
	{
		return new PreferencesLoader();
	}
	
	final void load()
	{
		Display.getDefault().syncExec(this.loader);
	}
	
	final void unload()
	{
		Display.getDefault().syncExec(this.unloader);
	}

	private static abstract class Hook implements Runnable
	{
		final ServiceSnapshot snapshot;

		Hook(final ServiceSnapshot snapshot)
		{
			this.snapshot = Preconditions.checkNotNull(snapshot);
		}
		
		public void run()
		{
		}
	}
	
	private static final class Loader extends Hook
	{
		Loader(final ServiceSnapshot snapshot)
		{
			super(snapshot);
		}

		@Override
		public final void run()
		{
			this.snapshot.servicesInStopOrder().stopAndWait();
			
			ServiceGroup<Service> services = this.snapshot.services();
			
			Services.setResourceService(createResourceService(services.fetch(ResourceService.class)));
			Services.setListenerService(createListenerService(services.fetch(ListenerService.class)));
			
			ServiceSnapshot.take().servicesInStartOrder().startAndWait();
		}
	}
	
	private static final class Unloader extends Hook
	{
		Unloader(final ServiceSnapshot snapshot)
		{
			super(snapshot);
		}
	}

	static final ResourceService createResourceService(final ResourceService service)
	{
		ResourceService.Builder builder = ResourceServices.builder();
		
		builder.provider(service.getResourceProvider());
		builder.manager(service.getResourceManager());
		
		builder.initializer(new ResourceInitializer()
		{	
			public final void run()
			{
				Registrations.applyRegisteredMark(ResourcePreferences.getInstance().getResourcePersistenceData());
			}
		});
				
		return builder.build();
	}

	static final ListenerService createListenerService(final ListenerService service)
	{
		ListenerService.Builder builder = ListenerServices.builder();
		
		builder.provider(service.getListenerProvider());
		builder.manager(service.getListenerManager());
		
		builder.initializer(new ListenerInitializer()
		{
			public final void run()
			{
				Registrations.applyRegisteredMark(ListenerPreferences.getInstance().getListenerPersistenceData());	
			}
		});
				
		return builder.build();
	}
}
