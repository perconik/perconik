package sk.stuba.fiit.perconik.debug.listeners;

import java.util.EnumSet;
import java.util.Set;
import org.eclipse.jdt.core.ElementChangedEvent;
import sk.stuba.fiit.perconik.core.listeners.JavaElementChangeListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementChangeEventType;

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
		this.printHeader("Java element changed");
		this.printJavaElementChangeEvent(event);
	}

	public final Set<JavaElementChangeEventType> getEventTypes()
	{
		return EnumSet.allOf(JavaElementChangeEventType.class);
	}
	
	private final void printJavaElementChangeEvent(final ElementChangedEvent event)
	{
		this.put(Debug.dumpJavaElementChangeEvent(event));
	}
}
