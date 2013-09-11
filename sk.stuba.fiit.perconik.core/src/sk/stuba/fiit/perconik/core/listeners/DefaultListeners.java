package sk.stuba.fiit.perconik.core.listeners;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManagers;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerServices;

/**
 * Static accessor methods pertaining to default listener core implementation.
 * 
 * <p>The core implementation includes default {@code Listener}
 * implementation classes along with respective {@code ListenerClassesSupplier}
 * as well as default {@code ListenerService}, {@code ListenerProvider}
 * and {@code ListenerManager}.
 * 
 * <p>The default implementations of listeners as well as listener provider,
 * manager and service are still available by this class even if the respective
 * listeners are unregistered from the core or the services are switched or
 * stopped.
 * 
 * <p><b>Note:</b> The core listener implementation currently does not include
 * any default listener implementations, but this is subject to change in the
 * future releases. 
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class DefaultListeners
{
	private DefaultListeners()
	{
		throw new AssertionError();
	}
	
	private static final class ProviderHolder
	{
		static final ListenerProvider instance;
		
		static
		{
			instance = ListenerProviders.builder().build();
		}
		
		private ProviderHolder()
		{
			throw new AssertionError();
		}
	}

	private static final class ManagerHolder
	{
		static final ListenerManager instance;
		
		static
		{
			instance = ListenerManagers.create();
		}
		
		private ManagerHolder()
		{
			throw new AssertionError();
		}
	}

	private static final class ServiceHolder
	{
		static final ListenerService instance;
		
		static
		{
			ListenerService.Builder builder = ListenerServices.builder();
			
			builder.provider(ProviderHolder.instance);
			builder.manager(ManagerHolder.instance);
			
			instance = builder.build();
		}
		
		private ServiceHolder()
		{
			throw new AssertionError();
		}
	}

	/**
	 * Returns the default listener provider. The returned provider is a
	 * standard listener provider constructed using the standard provider
	 * builder from {@link ListenerProviders#builder()} factory method.
	 * Its direct parent and the only predecessor in the listener provider
	 * hierarchy is the system listener provider.
	 * 
	 * <p>The default listener provider is lazily
	 * initialized at the first call of this method.
	 * 
	 * @return the default listener provider
	 * 
	 * @see ListenerProvider
	 * @see ListenerProviders#builder()
	 * @see ListenerProviders#getSystemProvider()
	 */
	public static final ListenerProvider getDefaultListenerProvider()
	{
		return ProviderHolder.instance;
	}

	/**
	 * Returns the default listener manager. The returned
	 * manager is a standard listener manager constructed by
	 * the {@link ListenerManagers#create()} factory method.
	 * 
	 * <p>The default listener manager is lazily
	 * initialized at the first call of this method.
	 * 
	 * @return the default listener manager
	 * 
	 * @see ListenerManager
	 * @see ListenerManagers#create()
	 */
	public static final ListenerManager getDefaultListenerManager()
	{
		return ManagerHolder.instance;
	}
	
	/**
	 * Returns the default listener service. The returned service is a
	 * standard listener service constructed using the standard service
	 * builder from {@link ListenerServices#builder()} factory method.
	 * It contains the default listener provider and manager.
	 * 
	 * <p>The default listener service is lazily
	 * initialized at the first call of this method.
	 * 
	 * <p><b>Note:</b> The returned service may be unusable if it
	 * has been retrieved by this method earlier and then stopped.
	 * 
	 * @return the default listener service
	 * 
	 * @see ListenerService
	 * @see ListenerServices#builder()
	 * @see #getDefaultListenerProvider()
	 * @see #getDefaultListenerManager()
	 */
	public static final ListenerService getDefaultListenerService()
	{
		return ServiceHolder.instance;
	}
	
	/**
	 * Returns the default listener implementation classes supplier.
	 * The built supplier dynamically supplies listener implementation
	 * classes based on the currently used {@code ListenerProvider} obtained
	 * by this {@code Services.getListenerService().getListenerProvider()}
	 * method call at supplying. 
	 * 
	 * <p><b>Note:</b> The returned supplier always supplies an empty set
	 * because the core listener implementation currently does not include
	 * any default listener implementations, but this is subject to change
	 * in the future releases.
	 * 
	 * @return the default listener classes supplier
	 * 
	 * @see ListenerClassesSupplier
	 * @see #getDefaultListenerProvider()
	 */
	public static final ListenerClassesSupplier getDefaultListenerClassesSupplier()
	{
		return new ListenerClassesSupplier()
		{
			public final Set<Class<? extends Listener>> get()
			{
				return Services.getListenerService().getListenerProvider().classes();
			}
		};
	}

// TODO rm or update

//	public static final void register(final CommandListener listener)
//	{
//		DefaultResources.getCommandResource().register(listener);
//	}
//	
//	public static final void register(final CommandExecutionListener listener)
//	{
//		DefaultResources.getCommandExecutionResource().register(listener);
//	}
//	
//	public static final void register(final CommandManagerListener listener)
//	{
//		DefaultResources.getCommandManagerResource().register(listener);
//	}
//	
//	public static final void register(final CompletionListener listener)
//	{
//		DefaultResources.getCompletionResource().register(listener);
//	}
//	
//	public static final void register(final DebugEventsListener listener)
//	{
//		DefaultResources.getDebugEventsResource().register(listener);
//	}
//	
//	public static final void register(final DocumentListener listener)
//	{
//		DefaultResources.getDocumentResource().register(listener);
//	}
//	
//	public static final void register(final FileBufferListener listener)
//	{
//		DefaultResources.getFileBufferResource().register(listener);
//	}
//	
//	public static final void register(final JavaElementListener listener)
//	{
//		DefaultResources.getJavaElementResource().register(listener);
//	}
//
//	public static final void register(final LaunchListener listener)
//	{
//		DefaultResources.getLaunchResource().register(listener);
//	}
//
//	public static final void register(final LaunchesListener listener)
//	{
//		DefaultResources.getLaunchesResource().register(listener);
//	}
//
//	public static final void register(final LaunchConfigurationListener listener)
//	{
//		DefaultResources.getLaunchConfigurationResource().register(listener);
//	}
//	
//	public static final void register(final OperationHistoryListener listener)
//	{
//		DefaultResources.getOperationHistoryResource().register(listener);
//	}
//
//	public static final void register(final PageListener listener)
//	{
//		DefaultResources.getPageResource().register(listener);
//	}
//
//	public static final void register(final PartListener listener)
//	{
//		DefaultResources.getPartResource().register(listener);
//	}
//	
//	public static final void register(final PerspectiveListener listener)
//	{
//		DefaultResources.getPerspectiveResource().register(listener);
//	}
//
//	public static final void register(final RefactoringExecutionListener listener)
//	{
//		DefaultResources.getRefactoringExecutionResource().register(listener);
//	}
//	
//	public static final void register(final RefactoringHistoryListener listener)
//	{
//		DefaultResources.getRefactoringHistoryResource().register(listener);
//	}
//
//	public static final void register(final ResourceListener listener)
//	{
//		DefaultResources.getResourceResource().register(listener);
//	}
//
//	public static final void register(final SelectionListener listener)
//	{
//		DefaultResources.getSelectionResource().register(listener);
//	}
//	
//	public static final void register(final TestRunListener listener)
//	{
//		DefaultResources.getTestRunResource().register(listener);
//	}
//
//	public static final void register(final WindowListener listener)
//	{
//		DefaultResources.getWindowResource().register(listener);
//	}
//
//	public static final void register(final WorkbenchListener listener)
//	{
//		DefaultResources.getWorkbenchResource().register(listener);
//	}
//
//	public static final void unregister(final CommandListener listener)
//	{
//		DefaultResources.getCommandResource().unregister(listener);
//	}
//
//	public static final void unregister(final CommandExecutionListener listener)
//	{
//		DefaultResources.getCommandExecutionResource().unregister(listener);
//	}
//
//	public static final void unregister(final CommandManagerListener listener)
//	{
//		DefaultResources.getCommandManagerResource().unregister(listener);
//	}
//
//	public static final void unregister(final CompletionListener listener)
//	{
//		DefaultResources.getCompletionResource().unregister(listener);
//	}
//
//	public static final void unregister(final DebugEventsListener listener)
//	{
//		DefaultResources.getDebugEventsResource().unregister(listener);
//	}
//
//	public static final void unregister(final DocumentListener listener)
//	{
//		DefaultResources.getDocumentResource().unregister(listener);
//	}
//
//	public static final void unregister(final FileBufferListener listener)
//	{
//		DefaultResources.getFileBufferResource().unregister(listener);
//	}
//
//	public static final void unregister(final JavaElementListener listener)
//	{
//		DefaultResources.getJavaElementResource().unregister(listener);
//	}
//
//	public static final void unregister(final LaunchListener listener)
//	{
//		DefaultResources.getLaunchResource().unregister(listener);
//	}
//
//	public static final void unregister(final LaunchesListener listener)
//	{
//		DefaultResources.getLaunchesResource().unregister(listener);
//	}
//
//	public static final void unregister(final LaunchConfigurationListener listener)
//	{
//		DefaultResources.getLaunchConfigurationResource().unregister(listener);
//	}
//	
//	public static final void unregister(final OperationHistoryListener listener)
//	{
//		DefaultResources.getOperationHistoryResource().unregister(listener);
//	}
//
//	public static final void unregister(final PageListener listener)
//	{
//		DefaultResources.getPageResource().unregister(listener);
//	}
//
//	public static final void unregister(final PartListener listener)
//	{
//		DefaultResources.getPartResource().unregister(listener);
//	}
//
//	public static final void unregister(final PerspectiveListener listener)
//	{
//		DefaultResources.getPerspectiveResource().unregister(listener);
//	}
//
//	public static final void unregister(final RefactoringExecutionListener listener)
//	{
//		DefaultResources.getRefactoringExecutionResource().unregister(listener);
//	}
//	
//	public static final void unregister(final RefactoringHistoryListener listener)
//	{
//		DefaultResources.getRefactoringHistoryResource().unregister(listener);
//	}
//
//	public static final void unregister(final ResourceListener listener)
//	{
//		DefaultResources.getResourceResource().unregister(listener);
//	}
//
//	public static final void unregister(final SelectionListener listener)
//	{
//		DefaultResources.getSelectionResource().unregister(listener);
//	}
//	
//	public static final void unregister(final TestRunListener listener)
//	{
//		DefaultResources.getTestRunResource().unregister(listener);
//	}
//
//	public static final void unregister(final WindowListener listener)
//	{
//		DefaultResources.getWindowResource().unregister(listener);
//	}
//
//	public static final void unregister(final WorkbenchListener listener)
//	{
//		DefaultResources.getWorkbenchResource().unregister(listener);
//	}
}
