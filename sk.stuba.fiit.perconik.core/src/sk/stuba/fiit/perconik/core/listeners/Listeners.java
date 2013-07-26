package sk.stuba.fiit.perconik.core.listeners;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class Listeners
{
	private Listeners()
	{
		throw new AssertionError();
	}

	public static final void register(final FileBufferListener listener)
	{
		Resources.getFileBufferResource().register(listener);
	}
	
	public static final void register(final LaunchListener listener)
	{
		Resources.getLaunchResource().register(listener);
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
	
	public static final void register(final RefactoringExecutionListener listener)
	{
		Resources.getRefactoringExecutionResource().register(listener);
	}
	
	public static final void register(final SelectionListener listener)
	{
		Resources.getSelectionResource().register(listener);
	}
	
	public static final void unregister(final FileBufferListener listener)
	{
		Resources.getFileBufferResource().unregister(listener);
	}

	public static final void unregister(final LaunchListener listener)
	{
		Resources.getLaunchResource().unregister(listener);
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

	public static final void unregister(final RefactoringExecutionListener listener)
	{
		Resources.getRefactoringExecutionResource().unregister(listener);
	}
	
	public static final void unregister(final SelectionListener listener)
	{
		Resources.getSelectionResource().unregister(listener);
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
