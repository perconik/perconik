package sk.stuba.fiit.perconik.debug.plugin;

import java.util.Set;
import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.services.Service;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManagers;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerServices;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManagers;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceServices;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.services.listeners.DebugListenerManagerProxy;
import sk.stuba.fiit.perconik.debug.services.listeners.DebugListenerProviderProxy;
import sk.stuba.fiit.perconik.debug.services.resources.DebugResourceManagerProxy;
import sk.stuba.fiit.perconik.debug.services.resources.DebugResourceProviderProxy;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Service.State;

class DebugLoader
{
	private final ServiceSnapshot snapshot;
	
	private final Loader loader;
	
	private final Unloader unloader;
	
	private DebugLoader()
	{
		this.snapshot = new ServiceSnapshot();
		
		this.loader   = new Loader(this.snapshot);
		this.unloader = new Unloader(this.snapshot);
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

	private static final class Loader implements Runnable
	{
		final ServiceSnapshot snapshot;

		Loader(final ServiceSnapshot snapshot)
		{
			this.snapshot = Preconditions.checkNotNull(snapshot);
		}

		public final void run()
		{
			Debug.put("Waiting for default services to start ... ");

			this.snapshot.services.waitForState(State.RUNNING);
			
			Debug.print("done");
			
			ResourceService resourceService = prepareDebugResourceServiceFrom(this.snapshot.resourceService);
			ListenerService listenerService = prepareDebugListenerServiceFrom(this.snapshot.listenerService);
			
//			ServiceGroup<Service> services = ServiceGroup.of(resourceService, listenerService);
			
			// TODO(note) do not stop default services so they can be restored later
			
//			Services.stop();
//			
//			Debug.put("Waiting for default services to stop ... ");
//
//			this.snapshot.services.waitForState(State.TERMINATED);
//			
//			Debug.print("done");
			
			Debug.put("Switching default services to debug services ... ");
			
			Services.setResourceService(resourceService);
			Services.setListenerService(listenerService);
					
			Debug.print("done");
			
			Debug.print("Starting debug resource service:");
			Debug.tab();
			
			resourceService.startAndWait();
			
			Debug.untab();
			Debug.print("Debug resource service running");
			Debug.print("Starting debug listener service:");
			Debug.tab();
			
			listenerService.startAndWait();
			
			Debug.untab();
			Debug.print("Debug listener service running");
			
//			services.waitForState(State.RUNNING);
		}
	}
	
	private static final class Unloader implements Runnable
	{
		final ServiceSnapshot snapshot;

		Unloader(final ServiceSnapshot snapshot)
		{
			this.snapshot = Preconditions.checkNotNull(snapshot);
		}
		
		public final void run()
		{
			ResourceService resourceService = Services.getResourceService();
			ListenerService listenerService = Services.getListenerService();
			
//			ServiceGroup<Service> services = ServiceGroup.of(resourceService, listenerService);
			
			Debug.print("Stopping debug listener service:");
			Debug.tab();

			listenerService.stopAndWait();

			Debug.untab();
			Debug.print("Debug listener service terminated");
			Debug.print("Stopping debug resource service:");
			Debug.tab();
	
			resourceService.stopAndWait();

			Debug.untab();
			Debug.print("Debug resource service terminated");
			
//			services.waitForState(State.TERMINATED);
			
//			Debug.untab();
//			Debug.print("Debug services terminated");
			
			Debug.put("Switching debug services back to default services ... ");
			
			Services.setResourceService(this.snapshot.resourceService);
			Services.setListenerService(this.snapshot.listenerService);
					
			Debug.print("done");

//			Services.start();
//			
//			Debug.put("Waiting for default services to start ... ");
//
//			this.snapshot.services.waitForState(State.RUNNING);
//			
//			Debug.print("done");
		}
	}
	
	static final ResourceService prepareDebugResourceServiceFrom(final ResourceService service)
	{
		Debug.print("Preparing debug resource service:");
		Debug.tab();

		Debug.put("Wrapping resource provider ... ");
		
		ResourceProvider provider = DebugResourceProviderProxy.wrap(service.getResourceProvider());
		
		Debug.print("done");
		Debug.put("Creating resource manager ... ");
		
		ResourceManager manager = DebugResourceManagerProxy.wrap(ResourceManagers.create());

		Debug.print("done");
		Debug.put("Creating debug resource service ... ");
		
		ResourceService result = ResourceServices.create(provider, manager);
		
		Debug.print("done");

		Debug.untab();
		
		return result;
	}
	
	static final ListenerService prepareDebugListenerServiceFrom(final ListenerService service)
	{
		Debug.print("Preparing debug listener service:");
		Debug.tab();

		Debug.put("Wrapping listener provider ... ");
		
		ListenerProvider provider = DebugListenerProviderProxy.wrap(DebugListeners.getListenerProvider());// TODO fix
		
		Debug.print("done");
		Debug.put("Creating listener manager ... ");
		
		ListenerManager manager = DebugListenerManagerProxy.wrap(ListenerManagers.create());

		Debug.print("done");
		Debug.put("Creating debug listener service ... ");
		
		ListenerService result = ListenerServices.create(provider, manager);
		
		Debug.print("done");
		
		Debug.untab();
		
		return result;
	}
	
	static final class ServiceSnapshot
	{
		final ResourceService resourceService;
		
		final ListenerService listenerService;
		
		final ServiceGroup<Service> services;
	
		ServiceSnapshot()
		{
			this.resourceService = Services.getResourceService();
			this.listenerService = Services.getListenerService();
			
			this.services = ServiceGroup.of(this.resourceService, this.listenerService);
		}
	}

	static final class ServiceGroup<S extends Service>
	{
		private final Set<S> services;
		
		private ServiceGroup(final Set<S> services)
		{
			Preconditions.checkArgument(!services.isEmpty());
			
			this.services = ImmutableSet.copyOf(services);
		}
		
		@SafeVarargs
		static final <S extends Service> ServiceGroup<S> of(final S ... services)
		{
			return new ServiceGroup<>(Sets.newHashSet(services));
		}
		
		final boolean inState(final State state)
		{
			boolean result = true;

			for (Service service: this.services)
			{
				result = result && (state.equals(service.state()));
			}
			
			return result;
		}
		
		final void waitForState(final State state)
		{
			while (!this.inState(state)) {}
		}

		@Override
		public final boolean equals(final Object o)
		{
			if (this == o)
			{
				return true;
			}
			
			if (!(o instanceof ServiceGroup))
			{
				return false;
			}
			
			return this.services.equals(((ServiceGroup<?>) o).services);
		}

		@Override
		public final int hashCode()
		{
			return this.services.hashCode();
		}

		@Override
		public final String toString()
		{
			return this.services.toString();
		}

		final Set<S> getServices()
		{
			return this.services;
		}
	}
}
