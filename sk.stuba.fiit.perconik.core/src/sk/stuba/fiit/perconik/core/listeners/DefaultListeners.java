package sk.stuba.fiit.perconik.core.listeners;

import sk.stuba.fiit.perconik.core.resources.DefaultResources;
import sk.stuba.fiit.perconik.core.services.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.ListenerService;

public final class DefaultListeners
{
	private DefaultListeners()
	{
		throw new AssertionError();
	}
	
	public static final void register(final CommandChangeListener listener)
	{
		DefaultResources.getCommandChangeResource().register(listener);
	}
	
	public static final void register(final CommandExecutionListener listener)
	{
		DefaultResources.getCommandExecutionResource().register(listener);
	}
	
	public static final void register(final CommandManagerChangeListener listener)
	{
		DefaultResources.getCommandManagerChangeResource().register(listener);
	}
	
	public static final void register(final CompletionListener listener)
	{
		DefaultResources.getCompletionResource().register(listener);
	}
	
	public static final void register(final DebugEventsListener listener)
	{
		DefaultResources.getDebugEventsResource().register(listener);
	}
	
	public static final void register(final DocumentChangeListener listener)
	{
		DefaultResources.getDocumentChangeResource().register(listener);
	}
	
	public static final void register(final FileBufferListener listener)
	{
		DefaultResources.getFileBufferResource().register(listener);
	}
	
	public static final void register(final JavaElementChangeListener listener)
	{
		DefaultResources.getJavaElementChangeResource().register(listener);
	}

	public static final void register(final LaunchListener listener)
	{
		DefaultResources.getLaunchResource().register(listener);
	}

	public static final void register(final LaunchesListener listener)
	{
		DefaultResources.getLaunchesResource().register(listener);
	}

	public static final void register(final LaunchConfigurationListener listener)
	{
		DefaultResources.getLaunchConfigurationResource().register(listener);
	}
	
	public static final void register(final OperationHistoryListener listener)
	{
		DefaultResources.getOperationHistoryResource().register(listener);
	}

	public static final void register(final PageListener listener)
	{
		DefaultResources.getPageResource().register(listener);
	}

	public static final void register(final PartListener listener)
	{
		DefaultResources.getPartResource().register(listener);
	}
	
	public static final void register(final PerspectiveListener listener)
	{
		DefaultResources.getPerspectiveResource().register(listener);
	}

	public static final void register(final RefactoringExecutionListener listener)
	{
		DefaultResources.getRefactoringExecutionResource().register(listener);
	}
	
	public static final void register(final RefactoringHistoryListener listener)
	{
		DefaultResources.getRefactoringHistoryResource().register(listener);
	}

	public static final void register(final ResourceChangeListener listener)
	{
		DefaultResources.getResourceChangeResource().register(listener);
	}

	public static final void register(final SelectionListener listener)
	{
		DefaultResources.getSelectionResource().register(listener);
	}
	
	public static final void register(final TestRunListener listener)
	{
		DefaultResources.getTestRunResource().register(listener);
	}

	public static final void register(final WindowListener listener)
	{
		DefaultResources.getWindowResource().register(listener);
	}

	public static final void register(final WorkbenchListener listener)
	{
		DefaultResources.getWorkbenchResource().register(listener);
	}

	public static final void unregister(final CommandChangeListener listener)
	{
		DefaultResources.getCommandChangeResource().unregister(listener);
	}

	public static final void unregister(final CommandExecutionListener listener)
	{
		DefaultResources.getCommandExecutionResource().unregister(listener);
	}

	public static final void unregister(final CommandManagerChangeListener listener)
	{
		DefaultResources.getCommandManagerChangeResource().unregister(listener);
	}

	public static final void unregister(final CompletionListener listener)
	{
		DefaultResources.getCompletionResource().unregister(listener);
	}

	public static final void unregister(final DebugEventsListener listener)
	{
		DefaultResources.getDebugEventsResource().unregister(listener);
	}

	public static final void unregister(final DocumentChangeListener listener)
	{
		DefaultResources.getDocumentChangeResource().unregister(listener);
	}

	public static final void unregister(final FileBufferListener listener)
	{
		DefaultResources.getFileBufferResource().unregister(listener);
	}

	public static final void unregister(final JavaElementChangeListener listener)
	{
		DefaultResources.getJavaElementChangeResource().unregister(listener);
	}

	public static final void unregister(final LaunchListener listener)
	{
		DefaultResources.getLaunchResource().unregister(listener);
	}

	public static final void unregister(final LaunchesListener listener)
	{
		DefaultResources.getLaunchesResource().unregister(listener);
	}

	public static final void unregister(final LaunchConfigurationListener listener)
	{
		DefaultResources.getLaunchConfigurationResource().unregister(listener);
	}
	
	public static final void unregister(final OperationHistoryListener listener)
	{
		DefaultResources.getOperationHistoryResource().unregister(listener);
	}

	public static final void unregister(final PageListener listener)
	{
		DefaultResources.getPageResource().unregister(listener);
	}

	public static final void unregister(final PartListener listener)
	{
		DefaultResources.getPartResource().unregister(listener);
	}

	public static final void unregister(final PerspectiveListener listener)
	{
		DefaultResources.getPerspectiveResource().unregister(listener);
	}

	public static final void unregister(final RefactoringExecutionListener listener)
	{
		DefaultResources.getRefactoringExecutionResource().unregister(listener);
	}
	
	public static final void unregister(final RefactoringHistoryListener listener)
	{
		DefaultResources.getRefactoringHistoryResource().unregister(listener);
	}

	public static final void unregister(final ResourceChangeListener listener)
	{
		DefaultResources.getResourceChangeResource().unregister(listener);
	}

	public static final void unregister(final SelectionListener listener)
	{
		DefaultResources.getSelectionResource().unregister(listener);
	}
	
	public static final void unregister(final TestRunListener listener)
	{
		DefaultResources.getTestRunResource().unregister(listener);
	}

	public static final void unregister(final WindowListener listener)
	{
		DefaultResources.getWindowResource().unregister(listener);
	}

	public static final void unregister(final WorkbenchListener listener)
	{
		DefaultResources.getWorkbenchResource().unregister(listener);
	}

	private static final class ServiceHolder
	{
		static final ListenerService service = new GenericListenerService();
		
		private ServiceHolder()
		{
			throw new AssertionError();
		}
	}

	private static final class ProviderHolder
	{
		static final ListenerProvider provider = new GenericListenerProvider();
		
		private ProviderHolder()
		{
			throw new AssertionError();
		}
	}

	public static final ListenerService getDefaultListenerService()
	{
		return ServiceHolder.service;
	}

	public static final ListenerProvider getDefaultListenerProvider()
	{
		return ProviderHolder.provider;
	}
}
