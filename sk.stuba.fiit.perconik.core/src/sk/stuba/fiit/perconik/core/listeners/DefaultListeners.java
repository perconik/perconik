package sk.stuba.fiit.perconik.core.listeners;

import static sk.stuba.fiit.perconik.core.resources.DefaultResources.registerAndHookListener;
import static sk.stuba.fiit.perconik.core.resources.DefaultResources.unregisterAndHookListener;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;
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

	public static final void register(final CommandListener listener)
	{
		registerAndHookListener(DefaultResources.getCommandResource(), listener);
	}

	public static final void register(final CommandCategoryListener listener)
	{
		registerAndHookListener(DefaultResources.getCommandCategoryResource(), listener);
	}

	public static final void register(final CommandContextListener listener)
	{
		registerAndHookListener(DefaultResources.getCommandContextResource(), listener);
	}

	public static final void register(final CommandContextManagerListener listener)
	{
		registerAndHookListener(DefaultResources.getCommandContextManagerResource(), listener);
	}

	public static final void register(final CommandExecutionListener listener)
	{
		registerAndHookListener(DefaultResources.getCommandExecutionResource(), listener);
	}
	
	public static final void register(final CommandHandlerListener listener)
	{
		registerAndHookListener(DefaultResources.getCommandHandlerResource(), listener);
	}
	
	public static final void register(final CommandManagerListener listener)
	{
		registerAndHookListener(DefaultResources.getCommandManagerResource(), listener);
	}
	
	public static final void register(final CompletionListener listener)
	{
		registerAndHookListener(DefaultResources.getCompletionResource(), listener);
	}
	
	public static final void register(final DebugEventsListener listener)
	{
		registerAndHookListener(DefaultResources.getDebugEventsResource(), listener);
	}
	
	public static final void register(final DocumentListener listener)
	{
		registerAndHookListener(DefaultResources.getDocumentResource(), listener);
	}
	
	public static final void register(final EditorListener listener)
	{
		registerAndHookListener(DefaultResources.getEditorResource(), listener);
	}
	
	public static final void register(final FileBufferListener listener)
	{
		registerAndHookListener(DefaultResources.getFileBufferResource(), listener);
	}
	
	public static final void register(final GitConfigurationListener listener)
	{
		registerAndHookListener(DefaultResources.getGitConfigurationResource(), listener);
	}

	public static final void register(final GitIndexListener listener)
	{
		registerAndHookListener(DefaultResources.getGitIndexResource(), listener);
	}

	public static final void register(final GitReferenceListener listener)
	{
		registerAndHookListener(DefaultResources.getGitReferenceResource(), listener);
	}

	public static final void register(final JavaElementListener listener)
	{
		registerAndHookListener(DefaultResources.getJavaElementResource(), listener);
	}

	public static final void register(final LaunchListener listener)
	{
		registerAndHookListener(DefaultResources.getLaunchResource(), listener);
	}

	public static final void register(final LaunchesListener listener)
	{
		registerAndHookListener(DefaultResources.getLaunchesResource(), listener);
	}

	public static final void register(final LaunchConfigurationListener listener)
	{
		registerAndHookListener(DefaultResources.getLaunchConfigurationResource(), listener);
	}
	
	public static final void register(final MarkSelectionListener listener)
	{
		registerAndHookListener(DefaultResources.getMarkSelectionResource(), listener);
	}

	public static final void register(final OperationHistoryListener listener)
	{
		registerAndHookListener(DefaultResources.getOperationHistoryResource(), listener);
	}

	public static final void register(final PageListener listener)
	{
		registerAndHookListener(DefaultResources.getPageResource(), listener);
	}

	public static final void register(final PartListener listener)
	{
		registerAndHookListener(DefaultResources.getPartResource(), listener);
	}
	
	public static final void register(final PerspectiveListener listener)
	{
		registerAndHookListener(DefaultResources.getPerspectiveResource(), listener);
	}

	public static final void register(final RefactoringExecutionListener listener)
	{
		registerAndHookListener(DefaultResources.getRefactoringExecutionResource(), listener);
	}
	
	public static final void register(final RefactoringHistoryListener listener)
	{
		registerAndHookListener(DefaultResources.getRefactoringHistoryResource(), listener);
	}

	public static final void register(final ResourceListener listener)
	{
		registerAndHookListener(DefaultResources.getResourceResource(), listener);
	}

	public static final void register(final SelectionListener listener)
	{
		registerAndHookListener(DefaultResources.getSelectionResource(), listener);
	}
	
	public static final void register(final StructuredSelectionListener listener)
	{
		registerAndHookListener(DefaultResources.getStructuredSelectionResource(), listener);
	}

	public static final void register(final TestRunListener listener)
	{
		registerAndHookListener(DefaultResources.getTestRunResource(), listener);
	}

	public static final void register(final TextSelectionListener listener)
	{
		registerAndHookListener(DefaultResources.getTextSelectionResource(), listener);
	}

	public static final void register(final WindowListener listener)
	{
		registerAndHookListener(DefaultResources.getWindowResource(), listener);
	}

	public static final void register(final WorkbenchListener listener)
	{
		registerAndHookListener(DefaultResources.getWorkbenchResource(), listener);
	}
	
	public static final void unregister(final CommandListener listener)
	{
		unregisterAndHookListener(DefaultResources.getCommandResource(), listener);
	}

	public static final void unregister(final CommandCategoryListener listener)
	{
		unregisterAndHookListener(DefaultResources.getCommandCategoryResource(), listener);
	}

	public static final void unregister(final CommandContextListener listener)
	{
		unregisterAndHookListener(DefaultResources.getCommandContextResource(), listener);
	}

	public static final void unregister(final CommandContextManagerListener listener)
	{
		unregisterAndHookListener(DefaultResources.getCommandContextManagerResource(), listener);
	}

	public static final void unregister(final CommandExecutionListener listener)
	{
		unregisterAndHookListener(DefaultResources.getCommandExecutionResource(), listener);
	}
	
	public static final void unregister(final CommandHandlerListener listener)
	{
		unregisterAndHookListener(DefaultResources.getCommandHandlerResource(), listener);
	}
	
	public static final void unregister(final CommandManagerListener listener)
	{
		unregisterAndHookListener(DefaultResources.getCommandManagerResource(), listener);
	}
	
	public static final void unregister(final CompletionListener listener)
	{
		unregisterAndHookListener(DefaultResources.getCompletionResource(), listener);
	}
	
	public static final void unregister(final DebugEventsListener listener)
	{
		unregisterAndHookListener(DefaultResources.getDebugEventsResource(), listener);
	}
	
	public static final void unregister(final DocumentListener listener)
	{
		unregisterAndHookListener(DefaultResources.getDocumentResource(), listener);
	}
	
	public static final void unregister(final EditorListener listener)
	{
		unregisterAndHookListener(DefaultResources.getEditorResource(), listener);
	}
	
	public static final void unregister(final FileBufferListener listener)
	{
		unregisterAndHookListener(DefaultResources.getFileBufferResource(), listener);
	}
	
	public static final void unregister(final GitConfigurationListener listener)
	{
		unregisterAndHookListener(DefaultResources.getGitConfigurationResource(), listener);
	}

	public static final void unregister(final GitIndexListener listener)
	{
		unregisterAndHookListener(DefaultResources.getGitIndexResource(), listener);
	}

	public static final void unregister(final GitReferenceListener listener)
	{
		unregisterAndHookListener(DefaultResources.getGitReferenceResource(), listener);
	}
	
	public static final void unregister(final JavaElementListener listener)
	{
		unregisterAndHookListener(DefaultResources.getJavaElementResource(), listener);
	}

	public static final void unregister(final LaunchListener listener)
	{
		unregisterAndHookListener(DefaultResources.getLaunchResource(), listener);
	}

	public static final void unregister(final LaunchesListener listener)
	{
		unregisterAndHookListener(DefaultResources.getLaunchesResource(), listener);
	}

	public static final void unregister(final LaunchConfigurationListener listener)
	{
		unregisterAndHookListener(DefaultResources.getLaunchConfigurationResource(), listener);
	}
	
	public static final void unregister(final MarkSelectionListener listener)
	{
		unregisterAndHookListener(DefaultResources.getMarkSelectionResource(), listener);
	}

	public static final void unregister(final OperationHistoryListener listener)
	{
		unregisterAndHookListener(DefaultResources.getOperationHistoryResource(), listener);
	}

	public static final void unregister(final PageListener listener)
	{
		unregisterAndHookListener(DefaultResources.getPageResource(), listener);
	}

	public static final void unregister(final PartListener listener)
	{
		unregisterAndHookListener(DefaultResources.getPartResource(), listener);
	}
	
	public static final void unregister(final PerspectiveListener listener)
	{
		unregisterAndHookListener(DefaultResources.getPerspectiveResource(), listener);
	}

	public static final void unregister(final RefactoringExecutionListener listener)
	{
		unregisterAndHookListener(DefaultResources.getRefactoringExecutionResource(), listener);
	}
	
	public static final void unregister(final RefactoringHistoryListener listener)
	{
		unregisterAndHookListener(DefaultResources.getRefactoringHistoryResource(), listener);
	}

	public static final void unregister(final ResourceListener listener)
	{
		unregisterAndHookListener(DefaultResources.getResourceResource(), listener);
	}

	public static final void unregister(final SelectionListener listener)
	{
		unregisterAndHookListener(DefaultResources.getSelectionResource(), listener);
	}
	
	public static final void unregister(final StructuredSelectionListener listener)
	{
		unregisterAndHookListener(DefaultResources.getStructuredSelectionResource(), listener);
	}

	public static final void unregister(final TestRunListener listener)
	{
		unregisterAndHookListener(DefaultResources.getTestRunResource(), listener);
	}

	public static final void unregister(final TextSelectionListener listener)
	{
		unregisterAndHookListener(DefaultResources.getTextSelectionResource(), listener);
	}

	public static final void unregister(final WindowListener listener)
	{
		unregisterAndHookListener(DefaultResources.getWindowResource(), listener);
	}

	public static final void unregister(final WorkbenchListener listener)
	{
		unregisterAndHookListener(DefaultResources.getWorkbenchResource(), listener);
	}
}
