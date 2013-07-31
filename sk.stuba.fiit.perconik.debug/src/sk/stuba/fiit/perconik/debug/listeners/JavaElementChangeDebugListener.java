package sk.stuba.fiit.perconik.debug.listeners;

import java.util.EnumSet;
import java.util.Set;
import org.eclipse.jdt.core.ElementChangedEvent;
import sk.stuba.fiit.perconik.core.listeners.JavaElementChangeListener;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.jdt.core.ElementChangedEventType;

public final class JavaElementChangeDebugListener extends AbstractDebugListener implements JavaElementChangeListener
{
	public JavaElementChangeDebugListener()
	{
	}
	
	public JavaElementChangeDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void elementChanged(final ElementChangedEvent event)
	{
		// TODO
	}

	public final Set<ElementChangedEventType> getEventTypes()
	{
		return EnumSet.allOf(ElementChangedEventType.class);
	}
}
