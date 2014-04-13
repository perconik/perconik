package com.gratex.perconik.activity.ide;

import static com.gratex.perconik.activity.ide.Internals.console;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;
import org.eclipse.jface.preference.IPreferenceStore;
import com.google.common.base.Optional;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.Maps;
import com.google.common.collect.MutableClassToInstanceMap;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys;
import com.gratex.perconik.activity.ide.ui.IdeActivityMessageDialogs;
import com.gratex.perconik.services.IVsActivityWatcherService;
import com.gratex.perconik.services.VsActivityWatcherService;

public final class IdeActivityServices
{
	private static final Object lock = new Object();

	private static final ClassToInstanceMap<Object> services = MutableClassToInstanceMap.create();
	
	private static final Map<Class<?>, Exception> failures = Maps.newHashMap();
	
	private static final Executor executor = Executors.newCachedThreadPool();

	private IdeActivityServices()
	{
		throw new AssertionError();
	}

	public static final IVsActivityWatcherService createWatcherService(final URL url, final QName name)
	{
		VsActivityWatcherService service = new VsActivityWatcherService(url, name);

		return service.getBasicHttpBindingIVsActivityWatcherService();
	}
	
	public static final IVsActivityWatcherService resolveWatcherService(final URL url, final QName name)
	{
		synchronized (lock)
		{
			IVsActivityWatcherService service = probeWatcherService().orNull();
			
			if (service == null)
			{
				try
				{
					service = createWatcherService(url, name);
					
					services.putInstance(IVsActivityWatcherService.class, service);
				}
				catch (Exception failure)
				{
					String message = "Unable to resolve activity watcher service at " + url + " with name " + name;
					
					reportWatcherServiceFailure(message, failure);
				}
			}
			
			return service;
		}
	}
	
	public static final Optional<IVsActivityWatcherService> probeWatcherService()
	{
		synchronized (lock)
		{
			return Optional.fromNullable(services.getInstance(IVsActivityWatcherService.class));
		}
	}

	public static final void releaseWatcherService()
	{
		synchronized (lock)
		{
			services.remove(IVsActivityWatcherService.class);
			failures.remove(IVsActivityWatcherService.class);
		}
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
						String message = "Unable to perform activity watcher service operation, service not available";
						
						reportWatcherServiceFailure(message, new NullPointerException());
					}
				}
				catch (Exception e)
				{
					handleWatcherServiceFailure(e);
				}
			}
		};
		
		executor.execute(command);
	}

	static final void handleWatcherServiceFailure(@Nullable final Exception failure)
	{
		String message = "Unexpected failure of activity watcher service";
		
		releaseWatcherService();
		reportWatcherServiceFailure(message, failure);
	}
	
	static final void reportWatcherServiceFailure(final String message, @Nullable final Exception failure)
	{
		IPreferenceStore store = IdeActivityPreferences.getPreferenceStore();
		
		if (store.getBoolean(Keys.logErrors))
		{
			console.error(message, failure);
		}

		boolean failed;
		
		synchronized (lock)
		{
			failed = failures.containsKey(IVsActivityWatcherService.class);
			
			failures.put(IVsActivityWatcherService.class, failure);
		}
		
		if (!failed && store.getBoolean(Keys.displayErrors))
		{
			IdeActivityMessageDialogs.openError(Keys.displayErrors, message);
		}
	}

	public static interface ServiceOperation<S extends Object>
	{
		public void perform(S service);
	}
	
	public static interface WatcherServiceOperation extends ServiceOperation<IVsActivityWatcherService>
	{
	}
}
