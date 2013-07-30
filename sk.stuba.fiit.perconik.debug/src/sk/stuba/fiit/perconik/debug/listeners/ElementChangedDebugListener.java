package sk.stuba.fiit.perconik.debug.listeners;

import java.util.EnumSet;
import java.util.Set;
import org.eclipse.jdt.core.ElementChangedEvent;
import sk.stuba.fiit.perconik.core.listeners.ElementChangedListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.jdt.core.ElementChangedEventType;

public final class ElementChangedDebugListener extends AbstractDebugListener implements ElementChangedListener
{
	public ElementChangedDebugListener()
	{
	}
	
	public ElementChangedDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void elementChanged(final ElementChangedEvent event)
	{
	}

	public final Set<ElementChangedEventType> getEventTypes()
	{
		return EnumSet.allOf(ElementChangedEventType.class);
	}
}
