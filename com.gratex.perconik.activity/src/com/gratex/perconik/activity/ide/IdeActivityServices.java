package com.gratex.perconik.activity.ide;

import static com.gratex.perconik.activity.ide.Internals.console;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import com.gratex.perconik.activity.ide.plugin.Activator;
import com.gratex.perconik.services.vs.IVsActivityWatcherService;
import com.gratex.perconik.services.vs.VsActivityWatcherService;

public final class IdeActivityServices
{
	private static final Object lock = new Object();

	private static final ClassToInstanceMap<Object> services = MutableClassToInstanceMap.create();
	
	private static final Executor executor = Executors.newCachedThreadPool();

	private IdeActivityServices()
	{
		throw new AssertionError();
	}
	
	static final IVsActivityWatcherService newWatcherService()
	{
		return newWatcherService(IdeActivityDefaults.watcherUrl, IdeActivityDefaults.watcherName);
	}

	static final IVsActivityWatcherService newWatcherService(final URL url, final QName name)
	{
		VsActivityWatcherService service = new VsActivityWatcherService(url, name);

		return service.getBasicHttpBindingIVsActivityWatcherService();
	}
	
	static final IVsActivityWatcherService resolveWatcherService(final URL url, final QName name)
	{
		synchronized (lock)
		{
			IVsActivityWatcherService service = services.getInstance(IVsActivityWatcherService.class);
			
			if (service == null)
			{
				try
				{
					service = newWatcherService(url, name);
					
					services.putInstance(IVsActivityWatcherService.class, service);
				}
				catch (Exception failure)
				{
					console.error("Unable to construct activity watcher service at " + url + " with name " + name, failure);
				}
			}
			
			return service;
		}
	}
	
	static final void releaseWatcherService()
	{
		synchronized (lock)
		{
			services.remove(IVsActivityWatcherService.class);
		}
	}
	
	static final void reportWatcherServiceFailure(@Nullable final Exception failure)
	{
		synchronized (lock)
		{
			services.remove(IVsActivityWatcherService.class);
		}
		
		Activator.getDefault().getConsole().error("Unexpected failure of activity watcher service", failure);
	}

	public static final void performWatcherServiceOperation(final WatcherServiceOperation operation)
	{
		final Runnable command = new Runnable()
		{
			public final void run()
			{
				IVsActivityWatcherService service = null;
				
				try
				{
					URL   url  = IdeActivityPreferences.getWatcherServiceUrl();
					QName name = IdeActivityPreferences.getWatcherServiceName();
					
					service = resolveWatcherService(url, name);
					
					if (service != null)
					{
						operation.perform(service);
					}
					else
					{
						console.notice("Unable to perform activity watcher service operation, service not available");
					}
				}
				catch (Exception e)
				{
					reportWatcherServiceFailure(e);
				}
			}
		};
		
		executor.execute(command);
	}

	public static interface ServiceOperation<S extends Object>
	{
		public void perform(S service);
	}
	
	public static interface WatcherServiceOperation extends ServiceOperation<IVsActivityWatcherService>
	{
	}
}
