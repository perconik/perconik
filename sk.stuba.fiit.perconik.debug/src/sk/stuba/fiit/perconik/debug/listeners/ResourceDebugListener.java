package sk.stuba.fiit.perconik.debug.listeners;

import java.util.EnumSet;
import java.util.Set;
import org.eclipse.core.resources.IResourceChangeEvent;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;

public final class ResourceDebugListener extends AbstractDebugListener implements ResourceListener
{
	public ResourceDebugListener()
	{
	}
	
	public ResourceDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void resourceChanged(final IResourceChangeEvent event)
	{
		this.printHeader("Resource changed");
		this.printResourceChangeEvent(event);
	}

	public final Set<ResourceEventType> getEventTypes()
	{
		return EnumSet.allOf(ResourceEventType.class);
	}
	
	private final void printResourceChangeEvent(final IResourceChangeEvent event)
	{
		this.put(Debug.dumpResourceChangeEvent(event));
	}
}
