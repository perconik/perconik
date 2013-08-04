package sk.stuba.fiit.perconik.debug.listeners;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import sk.stuba.fiit.perconik.core.listeners.DefaultListeners;
import sk.stuba.fiit.perconik.core.listeners.Listener;
import sk.stuba.fiit.perconik.core.listeners.Listeners;
import sk.stuba.fiit.perconik.core.resources.Resource;
import sk.stuba.fiit.perconik.core.resources.Resources;
import sk.stuba.fiit.perconik.debug.plugin.Activator;
import sk.stuba.fiit.perconik.debug.resources.DebugResources;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.Strings;

public final class DebugListeners
{
	private DebugListeners()
	{
		throw new AssertionError();
	}
	
	public static final void registerAll()
	{
		PluginConsole console = Activator.getDefault().getConsole();
		
		// TODO load from configuration
		
		DefaultListeners.register(new CommandChangeDebugListener(console));
		DefaultListeners.register(new CommandExecutionDebugListener(console));
		DefaultListeners.register(new CommandManagerChangeDebugListener(console));
		DefaultListeners.register(new CompletionDebugListener(console));
		DefaultListeners.register(new DebugEventsDebugListener(console));
		DefaultListeners.register(new DocumentChangeDebugListener(console));
		DefaultListeners.register(new FileBufferDebugListener(console));
		DefaultListeners.register(new JavaElementChangeDebugListener(console));
		//DefaultListeners.register(new LaunchDebugListener(console));
		DefaultListeners.register(new LaunchesDebugListener(console));
		//DefaultListeners.register(new LaunchConfigurationDebugListener(console));
		DefaultListeners.register(new OperationHistoryDebugListener(console));
		DefaultListeners.register(new PageDebugListener(console));
		DefaultListeners.register(new PartDebugListener(console));
		DefaultListeners.register(new PerspectiveDebugListener(console));
		DefaultListeners.register(new RefactoringExecutionDebugListener(console));
		DefaultListeners.register(new RefactoringHistoryDebugListener(console));
		DefaultListeners.register(new ResourceChangeDebugListener(console));
		DefaultListeners.register(new SelectionDebugListener(console));
		DefaultListeners.register(new TestRunDebugListener(console));
		DefaultListeners.register(new WindowDebugListener(console));
		DefaultListeners.register(new WorkbenchDebugListener(console));
	}
	
	public static final void unregisterAll()
	{
		for (Resource<?> resource: Resources.forListener(Listener.class))
		{
			resource.unregisterAll(DebugListener.class);
		}
	}

	public static final String toString(final Listener listener)
	{
		return Strings.toCanonicalString(listener);
	}

	public static final void printRegistered(final PluginConsole console)
	{
		printRegistered(console, Listener.class);
	}
	
	public static final void printRegistered(final PluginConsole console, final Class<? extends Listener> type)
	{
		console.put(dumpRegistered(type));
	}
	
	public static final void printRegisteredMap(final PluginConsole console)
	{
		console.put(dumpRegisteredMap());
	}

	static final String dumpRegistered(final Class<? extends Listener> type)
	{
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.appendln("Registered listeners:").tab();
		
		Collection<? extends Listener> listeners = Listeners.registered(type);
		
		if (!listeners.isEmpty())
		{
			for (Listener listener: listeners)
			{
				builder.appendln(toString(listener));
			}
		}
		else
		{
			builder.appendln("none");
		}

		return builder.toString();
	}

	static final String dumpRegisteredMap()
	{
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.appendln("Registered resource to listeners map:").tab();
		
		Map<Resource<?>, Collection<Listener>> map = Listeners.registeredMap();
		
		if (!map.isEmpty())
		{
			for (Entry<Resource<?>, Collection<Listener>> entry: map.entrySet())
			{
				Resource<?> resource = entry.getKey();
				
				builder.appendln(DebugResources.toString(resource)).tab();
	
				Collection<Listener> listeners = entry.getValue();
				
				if (!listeners.isEmpty())
				{
					for (Listener listener: listeners)
					{
						builder.appendln(toString(listener));
					}
				}
				else
				{
					builder.appendln("none");
				}
				
				builder.untab();
			}
		}
		else
		{
			builder.appendln("none");
		}

		return builder.toString();
	}
}
