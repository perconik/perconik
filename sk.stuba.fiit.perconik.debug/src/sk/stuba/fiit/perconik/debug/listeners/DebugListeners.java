package sk.stuba.fiit.perconik.debug.listeners;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import sk.stuba.fiit.perconik.core.listeners.Listener;
import sk.stuba.fiit.perconik.core.listeners.Listeners;
import sk.stuba.fiit.perconik.core.listeners.Resource;
import sk.stuba.fiit.perconik.core.listeners.Resources;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.core.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.core.utilities.Strings;
import sk.stuba.fiit.perconik.debug.plugin.Activator;

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
		
		Listeners.register(new FileBufferDebugListener(console));
		//Listeners.register(new LaunchConfigurationDebugListener(console));
		Listeners.register(new LaunchDebugListener(console));
		Listeners.register(new OperationHistoryDebugListener(console));
		Listeners.register(new PageDebugListener(console));
		Listeners.register(new PartDebugListener(console));
		Listeners.register(new RefactoringExecutionDebugListener(console));
		Listeners.register(new SelectionDebugListener(console));
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
		
		builder.appendln("Registered listeners map:").tab();
		
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
