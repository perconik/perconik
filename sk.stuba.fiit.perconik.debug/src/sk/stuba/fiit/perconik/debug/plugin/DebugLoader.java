package sk.stuba.fiit.perconik.debug.plugin;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.services.ServiceSnapshot;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerInitializer;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerServices;
import sk.stuba.fiit.perconik.core.services.resources.ResourceInitializer;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceServices;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.services.listeners.DebugListenerInitializerProxy;
import sk.stuba.fiit.perconik.debug.services.listeners.DebugListenerManagers;
import sk.stuba.fiit.perconik.debug.services.listeners.DebugListenerProviders;
import sk.stuba.fiit.perconik.debug.services.resources.DebugResourceInitializerProxy;
import sk.stuba.fiit.perconik.debug.services.resources.DebugResourceManagers;
import sk.stuba.fiit.perconik.debug.services.resources.DebugResourceProviders;
import com.google.common.base.Preconditions;

class DebugLoader
{
	private final Loader loader;
	
	private final Unloader unloader;
	
	private DebugLoader()
	{
		ServiceSnapshot snapshot = ServiceSnapshot.take();
		
		this.loader   = new Loader(snapshot);
		this.unloader = new Unloader(snapshot);
	}
	
	static final DebugLoader create()
	{
		return new DebugLoader();
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
		
		@Override
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
			Debug.put("Waiting for default services to start ... ");

			this.snapshot.servicesInStartOrder().startAndWait();
			
			Debug.print("done");
			
			ResourceService resources = createResourceService(this.snapshot.services().fetch(ResourceService.class));
			ListenerService listeners = createListenerService(this.snapshot.services().fetch(ListenerService.class));
			
			Debug.put("Switching default services to debug services ... ");
			
			Services.setResourceService(resources);
			Services.setListenerService(listeners);
					
			Debug.print("done");
			
			Debug.print("Starting debug resource service:");
			Debug.tab();
			
			resources.startAndWait();
			
			Debug.untab();
			Debug.print("Debug resource service running");
			Debug.print("Starting debug listener service:");
			Debug.tab();
			
			listeners.startAndWait();
			
			Debug.untab();
			Debug.print("Debug listener service running");
		}
	}
	
	private static final class Unloader extends Hook
	{
		Unloader(final ServiceSnapshot snapshot)
		{
			super(snapshot);
		}
		
		@Override
		public final void run()
		{
			ResourceService resources = Services.getResourceService();
			ListenerService listeners = Services.getListenerService();
			
			Debug.print("Stopping debug listener service:");
			Debug.tab();

			listeners.stopAndWait();

			Debug.untab();
			Debug.print("Debug listener service terminated");
			Debug.print("Stopping debug resource service:");
			Debug.tab();
	
			resources.stopAndWait();

			Debug.untab();
			Debug.print("Debug resource service terminated");

			Debug.put("Switching debug services back to default services ... ");
			
			Services.setResourceService(this.snapshot.services().fetch(ResourceService.class));
			Services.setListenerService(this.snapshot.services().fetch(ListenerService.class));
					
			Debug.print("done");
		}
	}
	
	static final ResourceService createResourceService(final ResourceService service)
	{
		Debug.print("Preparing debug resource service:");
		Debug.tab();

		Debug.put("Creating resource provider ... ");
		
		ResourceProvider provider = DebugResourceProviders.create(service.getResourceProvider());
		
		Debug.print("done");
		Debug.put("Creating resource manager ... ");
		
		ResourceManager manager = DebugResourceManagers.create();

		Debug.print("done");
		Debug.put("Creating resource initializer ... ");
		
		ResourceInitializer initializer = DebugResourceInitializerProxy.wrap(service.getResourceInitializer());

		Debug.print("done");
		Debug.put("Creating debug resource service ... ");
		
		ResourceService.Builder builder = ResourceServices.builder();
		
		builder.provider(provider);
		builder.manager(manager);
		builder.initializer(initializer);
		
		ResourceService result = builder.build();
		
		Debug.print("done");

		Debug.untab();
		
		return result;
	}
	
	static final ListenerService createListenerService(final ListenerService service)
	{
		Debug.print("Preparing debug listener service:");
		Debug.tab();

		Debug.put("Creating listener provider ... ");
		
		ListenerProvider provider = DebugListenerProviders.create(service.getListenerProvider());
		
		Debug.print("done");
		Debug.put("Creating listener manager ... ");
		
		ListenerManager manager = DebugListenerManagers.create();

		Debug.print("done");
		Debug.put("Creating listener initializer ... ");
		
		ListenerInitializer initializer = DebugListenerInitializerProxy.wrap(service.getListenerInitializer());

		Debug.print("done");
		Debug.put("Creating debug listener service ... ");
		
		ListenerService.Builder builder = ListenerServices.builder();
		
		builder.provider(provider);
		builder.manager(manager);
		builder.initializer(initializer);
		
		ListenerService result = builder.build();
		
		Debug.print("done");
		
		Debug.untab();
		
		return result;
	}
}
