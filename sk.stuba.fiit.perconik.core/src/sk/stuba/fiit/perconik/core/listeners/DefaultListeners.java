package sk.stuba.fiit.perconik.core.listeners;

import static sk.stuba.fiit.perconik.core.resources.DefaultResources.registerTo;
import static sk.stuba.fiit.perconik.core.resources.DefaultResources.unregisterFrom;
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
		registerTo(DefaultResources.getCommandResource(), listener);
	}

	public static final void register(final CommandCategoryListener listener)
	{
		registerTo(DefaultResources.getCommandCategoryResource(), listener);
	}

	public static final void register(final CommandContextListener listener)
	{
		registerTo(DefaultResources.getCommandContextResource(), listener);
	}

	public static final void register(final CommandContextManagerListener listener)
	{
		registerTo(DefaultResources.getCommandContextManagerResource(), listener);
	}

	public static final void register(final CommandExecutionListener listener)
	{
		registerTo(DefaultResources.getCommandExecutionResource(), listener);
	}
	
	public static final void register(final CommandHandlerListener listener)
	{
		registerTo(DefaultResources.getCommandHandlerResource(), listener);
	}
	
	public static final void register(final CommandManagerListener listener)
	{
		registerTo(DefaultResources.getCommandManagerResource(), listener);
	}
	
	public static final void register(final CompletionListener listener)
	{
		registerTo(DefaultResources.getCompletionResource(), listener);
	}
	
	public static final void register(final DebugEventsListener listener)
	{
		registerTo(DefaultResources.getDebugEventsResource(), listener);
	}
	
	public static final void register(final DocumentListener listener)
	{
		registerTo(DefaultResources.getDocumentResource(), listener);
	}
	
	public static final void register(final EditorListener listener)
	{
		registerTo(DefaultResources.getEditorResource(), listener);
	}
	
	public static final void register(final FileBufferListener listener)
	{
		registerTo(DefaultResources.getFileBufferResource(), listener);
	}
	
	public static final void register(final GitConfigurationListener listener)
	{
		registerTo(DefaultResources.getGitConfigurationResource(), listener);
	}

	public static final void register(final GitIndexListener listener)
	{
		registerTo(DefaultResources.getGitIndexResource(), listener);
	}

	public static final void register(final GitReferenceListener listener)
	{
		registerTo(DefaultResources.getGitReferenceResource(), listener);
	}

	public static final void register(final JavaElementListener listener)
	{
		registerTo(DefaultResources.getJavaElementResource(), listener);
	}

	public static final void register(final LaunchListener listener)
	{
		registerTo(DefaultResources.getLaunchResource(), listener);
	}

	public static final void register(final LaunchesListener listener)
	{
		registerTo(DefaultResources.getLaunchesResource(), listener);
	}

	public static final void register(final LaunchConfigurationListener listener)
	{
		registerTo(DefaultResources.getLaunchConfigurationResource(), listener);
	}
	
	public static final void register(final MarkSelectionListener listener)
	{
		registerTo(DefaultResources.getMarkSelectionResource(), listener);
	}

	public static final void register(final OperationHistoryListener listener)
	{
		registerTo(DefaultResources.getOperationHistoryResource(), listener);
	}

	public static final void register(final PageListener listener)
	{
		registerTo(DefaultResources.getPageResource(), listener);
	}

	public static final void register(final PartListener listener)
	{
		registerTo(DefaultResources.getPartResource(), listener);
	}
	
	public static final void register(final PerspectiveListener listener)
	{
		registerTo(DefaultResources.getPerspectiveResource(), listener);
	}

	public static final void register(final RefactoringExecutionListener listener)
	{
		registerTo(DefaultResources.getRefactoringExecutionResource(), listener);
	}
	
	public static final void register(final RefactoringHistoryListener listener)
	{
		registerTo(DefaultResources.getRefactoringHistoryResource(), listener);
	}

	public static final void register(final ResourceListener listener)
	{
		registerTo(DefaultResources.getResourceResource(), listener);
	}

	public static final void register(final SelectionListener listener)
	{
		registerTo(DefaultResources.getSelectionResource(), listener);
	}
	
	public static final void register(final StructuredSelectionListener listener)
	{
		registerTo(DefaultResources.getStructuredSelectionResource(), listener);
	}

	public static final void register(final TestRunListener listener)
	{
		registerTo(DefaultResources.getTestRunResource(), listener);
	}

	public static final void register(final TextSelectionListener listener)
	{
		registerTo(DefaultResources.getTextSelectionResource(), listener);
	}

	public static final void register(final WindowListener listener)
	{
		registerTo(DefaultResources.getWindowResource(), listener);
	}

	public static final void register(final WorkbenchListener listener)
	{
		registerTo(DefaultResources.getWorkbenchResource(), listener);
	}
	
	public static final void unregister(final CommandListener listener)
	{
		unregisterFrom(DefaultResources.getCommandResource(), listener);
	}

	public static final void unregister(final CommandCategoryListener listener)
	{
		unregisterFrom(DefaultResources.getCommandCategoryResource(), listener);
	}

	public static final void unregister(final CommandContextListener listener)
	{
		unregisterFrom(DefaultResources.getCommandContextResource(), listener);
	}

	public static final void unregister(final CommandContextManagerListener listener)
	{
		unregisterFrom(DefaultResources.getCommandContextManagerResource(), listener);
	}

	public static final void unregister(final CommandExecutionListener listener)
	{
		unregisterFrom(DefaultResources.getCommandExecutionResource(), listener);
	}
	
	public static final void unregister(final CommandHandlerListener listener)
	{
		unregisterFrom(DefaultResources.getCommandHandlerResource(), listener);
	}
	
	public static final void unregister(final CommandManagerListener listener)
	{
		unregisterFrom(DefaultResources.getCommandManagerResource(), listener);
	}
	
	public static final void unregister(final CompletionListener listener)
	{
		unregisterFrom(DefaultResources.getCompletionResource(), listener);
	}
	
	public static final void unregister(final DebugEventsListener listener)
	{
		unregisterFrom(DefaultResources.getDebugEventsResource(), listener);
	}
	
	public static final void unregister(final DocumentListener listener)
	{
		unregisterFrom(DefaultResources.getDocumentResource(), listener);
	}
	
	public static final void unregister(final EditorListener listener)
	{
		unregisterFrom(DefaultResources.getEditorResource(), listener);
	}
	
	public static final void unregister(final FileBufferListener listener)
	{
		unregisterFrom(DefaultResources.getFileBufferResource(), listener);
	}
	
	public static final void unregister(final GitConfigurationListener listener)
	{
		unregisterFrom(DefaultResources.getGitConfigurationResource(), listener);
	}

	public static final void unregister(final GitIndexListener listener)
	{
		unregisterFrom(DefaultResources.getGitIndexResource(), listener);
	}

	public static final void unregister(final GitReferenceListener listener)
	{
		unregisterFrom(DefaultResources.getGitReferenceResource(), listener);
	}
	
	public static final void unregister(final JavaElementListener listener)
	{
		unregisterFrom(DefaultResources.getJavaElementResource(), listener);
	}

	public static final void unregister(final LaunchListener listener)
	{
		unregisterFrom(DefaultResources.getLaunchResource(), listener);
	}

	public static final void unregister(final LaunchesListener listener)
	{
		unregisterFrom(DefaultResources.getLaunchesResource(), listener);
	}

	public static final void unregister(final LaunchConfigurationListener listener)
	{
		unregisterFrom(DefaultResources.getLaunchConfigurationResource(), listener);
	}
	
	public static final void unregister(final MarkSelectionListener listener)
	{
		unregisterFrom(DefaultResources.getMarkSelectionResource(), listener);
	}

	public static final void unregister(final OperationHistoryListener listener)
	{
		unregisterFrom(DefaultResources.getOperationHistoryResource(), listener);
	}

	public static final void unregister(final PageListener listener)
	{
		unregisterFrom(DefaultResources.getPageResource(), listener);
	}

	public static final void unregister(final PartListener listener)
	{
		unregisterFrom(DefaultResources.getPartResource(), listener);
	}
	
	public static final void unregister(final PerspectiveListener listener)
	{
		unregisterFrom(DefaultResources.getPerspectiveResource(), listener);
	}

	public static final void unregister(final RefactoringExecutionListener listener)
	{
		unregisterFrom(DefaultResources.getRefactoringExecutionResource(), listener);
	}
	
	public static final void unregister(final RefactoringHistoryListener listener)
	{
		unregisterFrom(DefaultResources.getRefactoringHistoryResource(), listener);
	}

	public static final void unregister(final ResourceListener listener)
	{
		unregisterFrom(DefaultResources.getResourceResource(), listener);
	}

	public static final void unregister(final SelectionListener listener)
	{
		unregisterFrom(DefaultResources.getSelectionResource(), listener);
	}
	
	public static final void unregister(final StructuredSelectionListener listener)
	{
		unregisterFrom(DefaultResources.getStructuredSelectionResource(), listener);
	}

	public static final void unregister(final TestRunListener listener)
	{
		unregisterFrom(DefaultResources.getTestRunResource(), listener);
	}

	public static final void unregister(final TextSelectionListener listener)
	{
		unregisterFrom(DefaultResources.getTextSelectionResource(), listener);
	}

	public static final void unregister(final WindowListener listener)
	{
		unregisterFrom(DefaultResources.getWindowResource(), listener);
	}

	public static final void unregister(final WorkbenchListener listener)
	{
		unregisterFrom(DefaultResources.getWorkbenchResource(), listener);
	}
}
