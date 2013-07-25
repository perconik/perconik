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
		
		Listeners.register(new LaunchDebugListener(console));
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
		console.put(debugRegistered(type));
	}
	
	public static final void printRegisteredMap(final PluginConsole console)
	{
		console.put(debugRegisteredMap());
	}

	private static final String debugRegistered(final Class<? extends Listener> type)
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

	private static final String debugRegisteredMap()
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
