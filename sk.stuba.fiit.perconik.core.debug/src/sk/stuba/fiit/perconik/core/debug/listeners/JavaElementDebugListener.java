package sk.stuba.fiit.perconik.core.debug.listeners;

import java.util.EnumSet;
import java.util.Set;

import org.eclipse.jdt.core.ElementChangedEvent;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.JavaElementListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType;

public final class JavaElementDebugListener extends AbstractDebugListener implements JavaElementListener
{
	public JavaElementDebugListener()
	{
	}
	
	public JavaElementDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void elementChanged(final ElementChangedEvent event)
	{
		this.printHeader("Java element changed");
		this.printJavaElementChangeEvent(event);
	}

	public final Set<JavaElementEventType> getEventTypes()
	{
		return EnumSet.allOf(JavaElementEventType.class);
	}
	
	private final void printJavaElementChangeEvent(final ElementChangedEvent event)
	{
		this.put(Debug.dumpJavaElementChangeEvent(event));
	}
}
