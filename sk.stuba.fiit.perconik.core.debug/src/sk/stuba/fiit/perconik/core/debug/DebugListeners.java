package sk.stuba.fiit.perconik.core.debug;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.utilities.MoreStrings;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class DebugListeners
{
	private DebugListeners()
	{
		throw new AssertionError();
	}

	public static final String toString(final Class<? extends Listener> type)
	{
		return type.getName();
	}
	
	public static final String toString(final Listener listener)
	{
		return MoreStrings.toStringFallback(listener);
	}
	
	public static final void printRegistered()
	{
		printRegistered(Listener.class);
	}
	
	public static final void printRegistered(final Class<? extends Listener> type)
	{
		printRegistered(type, Debug.getDefaultConsole());
	}
	
	public static final void printRegistered(final Class<? extends Listener> type, final DebugConsole console)
	{
		console.put(dumpRegistered(type));
	}

	public static final void printRegistrations()
	{
		printRegisterations(Debug.getDefaultConsole());
	}
	
	public static final void printRegisterations(final DebugConsole console)
	{
		console.put(dumpRegistrations());
	}

	static final String dumpRegistered(final Class<? extends Listener> type)
	{
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.appendln("Registered listeners:").tab();
		
		List<? extends Listener> listeners = Lists.newArrayList(Listeners.registered(type));

		if (!listeners.isEmpty())
		{
			Collections.sort(listeners, MoreStrings.toStringComparator());
			
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

	static final String dumpRegistrations()
	{
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.appendln("Registered resource to listeners map:").tab();
		
		Multimap<Resource<?>, Listener> map = Listeners.registrations();

		SortedSet<Resource<?>> resources = Sets.newTreeSet(MoreStrings.toStringComparator());
		
		resources.addAll(map.keySet());
		
		if (!map.isEmpty())
		{
			for (Resource<?> resource: resources)
			{
				builder.appendln(DebugResources.toString(resource)).tab();
	
				List<Listener> listeners = Lists.newArrayList(map.get(resource));
				
				if (!listeners.isEmpty())
				{
					Collections.sort(listeners, MoreStrings.toStringComparator());
					
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
