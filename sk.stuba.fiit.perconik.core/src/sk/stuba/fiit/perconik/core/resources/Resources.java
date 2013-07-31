package sk.stuba.fiit.perconik.core.resources;

import java.util.Map.Entry;
import java.util.Set;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.core.listeners.JavaElementChangeListener;
import sk.stuba.fiit.perconik.core.listeners.LaunchConfigurationListener;
import sk.stuba.fiit.perconik.core.listeners.LaunchListener;
import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;
import sk.stuba.fiit.perconik.core.listeners.Listener;
import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;
import sk.stuba.fiit.perconik.core.listeners.PageListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;
import sk.stuba.fiit.perconik.core.listeners.RefactoringExecutionListener;
import sk.stuba.fiit.perconik.core.listeners.RefactoringHistoryListener;
import sk.stuba.fiit.perconik.core.listeners.ResourceChangeListener;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;
import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

public final class Resources
{
	private static final SetMultimap<Class<?>, Resource<?>> resources = Multimaps.synchronizedSetMultimap(HashMultimap.<Class<?>, Resource<?>>create());
	
	private Resources()
	{
		throw new AssertionError();
	}
	
	static final <T extends Listener> Resource<T> create(final Handler<T> handler)
	{
		return new GenericResource<>(Pools.getPoolFactory().create(handler));
	}

	public static final <T extends Listener> void register(final Class<T> type, final Resource<T> resource)
	{
		resources.put(Preconditions.checkNotNull(type), Preconditions.checkNotNull(resource));
	}
	
	public static final <T extends Listener> void unregister(final Class<T> type, final Resource<T> resource)
	{
		resources.remove(Preconditions.checkNotNull(type), Preconditions.checkNotNull(resource));
	}
		
	public static final <T extends Listener> Set<Resource<? extends T>> forListener(final Class<T> type)
	{
		Set<Resource<? extends T>> result = Sets.newHashSet();
		
		for (Entry<Class<?>, Resource<?>> entry: resources.entries())
		{
			boolean assignable = type.isAssignableFrom(entry.getKey());
			
			// TODO reconsider this matching
			if (assignable == false)
			{
				for (Class<?> supertype: type.getInterfaces())
				{
					if (supertype == entry.getKey())
					{
						assignable = true;
						
						break;
					}
				}
			}
			
			if (assignable)
			{
				result.add((Resource<? extends T>) entry.getValue());
			}
		}
		
		return result;
	}

	public static final Set<Resource<?>> getResources()
	{
		return Sets.newHashSet(resources.values());
	}

	public static final Resource<FileBufferListener> getFileBufferResource()
	{
		return KnownResources.fileBuffer;
	}

	public static final Resource<JavaElementChangeListener> getJavaElementChangeResource()
	{
		return KnownResources.javaElementChange;
	}

	public static final Resource<LaunchListener> getLaunchResource()
	{
		return KnownResources.launch;
	}
	
	public static final Resource<LaunchesListener> getLaunchesResource()
	{
		return KnownResources.launches;
	}

	public static final Resource<LaunchConfigurationListener> getLaunchConfigurationResource()
	{
		return KnownResources.launchConfiguration;
	}

	public static final Resource<OperationHistoryListener> getOperationHistoryResource()
	{
		return KnownResources.operationHistory;
	}

	public static final Resource<PageListener> getPageResource()
	{
		return KnownResources.page;
	}

	public static final Resource<PartListener> getPartResource()
	{
		return KnownResources.part;
	}

	public static final Resource<PerspectiveListener> getPerspectiveResource()
	{
		return KnownResources.perspective;
	}

	public static final Resource<RefactoringExecutionListener> getRefactoringExecutionResource()
	{
		return KnownResources.refactoringExecution;
	}

	public static final Resource<RefactoringHistoryListener> getRefactoringHistoryResource()
	{
		return KnownResources.refactoringHistory;
	}

	public static final Resource<ResourceChangeListener> getResourceChangeResource()
	{
		return KnownResources.resourceChange;
	}

	public static final Resource<SelectionListener> getSelectionResource()
	{
		return KnownResources.selection;
	}

	public static final Resource<TestRunListener> getTestRunResource()
	{
		return KnownResources.testRun;
	}

	public static final Resource<WindowListener> getWindowResource()
	{
		return KnownResources.window;
	}

	public static final Resource<WorkbenchListener> getWorkbenchResource()
	{
		return KnownResources.workbench;
	}
}
