package sk.stuba.fiit.perconik.debug.listeners;

import java.util.EnumSet;
import java.util.Set;
import org.eclipse.core.resources.IResourceChangeEvent;
import sk.stuba.fiit.perconik.core.listeners.ResourceChangeListener;
import sk.stuba.fiit.perconik.core.resources.ResourceChangeEventType;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

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
		// TODO
	}

	public final Set<ResourceChangeEventType> getEventTypes()
	{
		return EnumSet.allOf(ResourceChangeEventType.class);
	}
}
