package sk.stuba.fiit.perconik.core.listeners;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import sk.stuba.fiit.perconik.core.resources.Resource;
import sk.stuba.fiit.perconik.core.resources.Resources;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class Listeners
{
	private Listeners()
	{
		throw new AssertionError();
	}

	public static final void register(final CommandChangeListener listener)
	{
		Resources.getCommandChangeResource().register(listener);
	}
	
	public static final void register(final CommandExecutionListener listener)
	{
		Resources.getCommandExecutionResource().register(listener);
	}
	
	public static final void register(final CommandManagerChangeListener listener)
	{
		Resources.getCommandManagerChangeResource().register(listener);
	}
	
	public static final void register(final CompletionListener listener)
	{
		Resources.getCompletionResource().register(listener);
	}
	
	public static final void register(final DebugEventSetListener listener)
	{
		Resources.getDebugEventSetResource().register(listener);
	}
	
	public static final void register(final DocumentChangeListener listener)
	{
		Resources.getDocumentChangeResource().register(listener);
	}
	
	public static final void register(final FileBufferListener listener)
	{
		Resources.getFileBufferResource().register(listener);
	}
	
	public static final void register(final JavaElementChangeListener listener)
	{
		Resources.getJavaElementChangeResource().register(listener);
	}

	public static final void register(final LaunchListener listener)
	{
		Resources.getLaunchResource().register(listener);
	}

	public static final void register(final LaunchesListener listener)
	{
		Resources.getLaunchesResource().register(listener);
	}

	public static final void register(final LaunchConfigurationListener listener)
	{
		Resources.getLaunchConfigurationResource().register(listener);
	}
	
	public static final void register(final OperationHistoryListener listener)
	{
		Resources.getOperationHistoryResource().register(listener);
	}

	public static final void register(final PageListener listener)
	{
		Resources.getPageResource().register(listener);
	}

	public static final void register(final PartListener listener)
	{
		Resources.getPartResource().register(listener);
	}
	
	public static final void register(final PerspectiveListener listener)
	{
		Resources.getPerspectiveResource().register(listener);
	}

	public static final void register(final RefactoringExecutionListener listener)
	{
		Resources.getRefactoringExecutionResource().register(listener);
	}
	
	public static final void register(final RefactoringHistoryListener listener)
	{
		Resources.getRefactoringHistoryResource().register(listener);
	}

	public static final void register(final ResourceChangeListener listener)
	{
		Resources.getResourceChangeResource().register(listener);
	}

	public static final void register(final SelectionListener listener)
	{
		Resources.getSelectionResource().register(listener);
	}
	
	public static final void register(final TestRunListener listener)
	{
		Resources.getTestRunResource().register(listener);
	}

	public static final void register(final WindowListener listener)
	{
		Resources.getWindowResource().register(listener);
	}

	public static final void register(final WorkbenchListener listener)
	{
		Resources.getWorkbenchResource().register(listener);
	}

	public static final void unregister(final CommandChangeListener listener)
	{
		Resources.getCommandChangeResource().unregister(listener);
	}

	public static final void unregister(final CommandExecutionListener listener)
	{
		Resources.getCommandExecutionResource().unregister(listener);
	}

	public static final void unregister(final CommandManagerChangeListener listener)
	{
		Resources.getCommandManagerChangeResource().unregister(listener);
	}

	public static final void unregister(final CompletionListener listener)
	{
		Resources.getCompletionResource().unregister(listener);
	}

	public static final void unregister(final DebugEventSetListener listener)
	{
		Resources.getDebugEventSetResource().unregister(listener);
	}

	public static final void unregister(final DocumentChangeListener listener)
	{
		Resources.getDocumentChangeResource().unregister(listener);
	}

	public static final void unregister(final FileBufferListener listener)
	{
		Resources.getFileBufferResource().unregister(listener);
	}

	public static final void unregister(final JavaElementChangeListener listener)
	{
		Resources.getJavaElementChangeResource().unregister(listener);
	}

	public static final void unregister(final LaunchListener listener)
	{
		Resources.getLaunchResource().unregister(listener);
	}

	public static final void unregister(final LaunchesListener listener)
	{
		Resources.getLaunchesResource().unregister(listener);
	}

	public static final void unregister(final LaunchConfigurationListener listener)
	{
		Resources.getLaunchConfigurationResource().unregister(listener);
	}
	
	public static final void unregister(final OperationHistoryListener listener)
	{
		Resources.getOperationHistoryResource().unregister(listener);
	}

	public static final void unregister(final PageListener listener)
	{
		Resources.getPageResource().unregister(listener);
	}

	public static final void unregister(final PartListener listener)
	{
		Resources.getPartResource().unregister(listener);
	}

	public static final void unregister(final PerspectiveListener listener)
	{
		Resources.getPerspectiveResource().unregister(listener);
	}

	public static final void unregister(final RefactoringExecutionListener listener)
	{
		Resources.getRefactoringExecutionResource().unregister(listener);
	}
	
	public static final void unregister(final RefactoringHistoryListener listener)
	{
		Resources.getRefactoringHistoryResource().unregister(listener);
	}

	public static final void unregister(final ResourceChangeListener listener)
	{
		Resources.getResourceChangeResource().unregister(listener);
	}

	public static final void unregister(final SelectionListener listener)
	{
		Resources.getSelectionResource().unregister(listener);
	}
	
	public static final void unregister(final TestRunListener listener)
	{
		Resources.getTestRunResource().unregister(listener);
	}

	public static final void unregister(final WindowListener listener)
	{
		Resources.getWindowResource().unregister(listener);
	}

	public static final void unregister(final WorkbenchListener listener)
	{
		Resources.getWorkbenchResource().unregister(listener);
	}

	public static final Collection<Listener> registered()
	{
		return registered(Listener.class);
	}
	
	public static final <T extends Listener> Collection<T> registered(final Class<T> type)
	{
		List<T> listeners = Lists.newArrayList();
		
		for (Resource<? extends T> resource: Resources.forListener(type))
		{
			listeners.addAll(resource.getRegistered(type));
		}
		
		return listeners;
	}

	public static final Map<Resource<?>, Collection<Listener>> registeredMap()
	{
		Map<Resource<?>, Collection<Listener>> map = Maps.newHashMap();
		
		for (Resource<?> resource: Resources.getResources())
		{
			map.put(resource, resource.getRegistered(Listener.class));
		}
		
		return map;
	}

	public static final void unregisterAll()
	{
		unregisterAll(Listener.class);
	}
	
	public static final void unregisterAll(final Class<? extends Listener> type)
	{
		for (Resource<?> resource: Resources.forListener(type))
		{
			resource.unregisterAll(type);
		}
	}
}
