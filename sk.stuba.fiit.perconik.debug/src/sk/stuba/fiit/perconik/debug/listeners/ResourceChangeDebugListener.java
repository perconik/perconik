package sk.stuba.fiit.perconik.debug.listeners;

import java.util.EnumSet;
import java.util.Set;
import org.eclipse.core.resources.IResourceChangeEvent;
import sk.stuba.fiit.perconik.core.listeners.ResourceChangeListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceChangeEventType;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public final class ResourceChangeDebugListener extends AbstractDebugListener implements ResourceChangeListener
{
	public ResourceChangeDebugListener()
	{
	}
	
	public ResourceChangeDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void resourceChanged(final IResourceChangeEvent event)
	{
		this.printHeader("Resource changed");
		this.printResourceChangeEvent(event);
	}

	public final Set<ResourceChangeEventType> getEventTypes()
	{
		return EnumSet.allOf(ResourceChangeEventType.class);
	}
	
	private final void printResourceChangeEvent(final IResourceChangeEvent event)
	{
		this.put(Debug.dumpResourceChangeEvent(event));
	}
}
