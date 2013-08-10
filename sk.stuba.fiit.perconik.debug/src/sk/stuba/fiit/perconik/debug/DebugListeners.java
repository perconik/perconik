package sk.stuba.fiit.perconik.debug;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.debug.listeners.CompletionDebugListener;
import sk.stuba.fiit.perconik.debug.plugin.Activator;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
				
//		Listeners.register(new CommandChangeDebugListener(console));
//		Listeners.register(new CommandExecutionDebugListener(console));
//		Listeners.register(new CommandManagerChangeDebugListener(console));
//		Listeners.register(new CompletionDebugListener(console));
//		Listeners.register(new DebugEventsDebugListener(console));
//		Listeners.register(new DocumentChangeDebugListener(console));
//		Listeners.register(new FileBufferDebugListener(console));
//		Listeners.register(new JavaElementChangeDebugListener(console));
//		//Listeners.register(new LaunchDebugListener(console));
//		Listeners.register(new LaunchesDebugListener(console));
//		//Listeners.register(new LaunchConfigurationDebugListener(console));
//		Listeners.register(new OperationHistoryDebugListener(console));
//		Listeners.register(new PageDebugListener(console));
//		Listeners.register(new PartDebugListener(console));
//		Listeners.register(new PerspectiveDebugListener(console));
//		Listeners.register(new RefactoringExecutionDebugListener(console));
//		Listeners.register(new RefactoringHistoryDebugListener(console));
//		Listeners.register(new ResourceChangeDebugListener(console));
//		Listeners.register(new SelectionDebugListener(console));
//		Listeners.register(new TestRunDebugListener(console));
//		Listeners.register(new WindowDebugListener(console));
//		Listeners.register(new WorkbenchDebugListener(console));
	}
	
	public static final void unregisterAll()
	{
		for (Resource<?> resource: Resources.assignable(Listener.class))
		{
			resource.unregisterAll(DebugListener.class);
		}
	}

	public static final String toString(final Listener listener)
	{
		return Strings.toStringFallback(listener);
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
		
		List<? extends Listener> listeners = Lists.newArrayList(Listeners.registered(type));

		if (!listeners.isEmpty())
		{
			Collections.sort(listeners, Strings.toStringComparator());
			
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
		
		Map<Resource<?>, Collection<Listener>> map = Maps.newTreeMap(Strings.toStringComparator());
		
		map.putAll(Listeners.registeredMap());
		
		if (!map.isEmpty())
		{
			for (Entry<Resource<?>, Collection<Listener>> entry: map.entrySet())
			{
				Resource<?> resource = entry.getKey();
				
				builder.appendln(DebugResources.toString(resource)).tab();
	
				List<Listener> listeners = Lists.newArrayList(entry.getValue());
				
				if (!listeners.isEmpty())
				{
					Collections.sort(listeners, Strings.toStringComparator());
					
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
