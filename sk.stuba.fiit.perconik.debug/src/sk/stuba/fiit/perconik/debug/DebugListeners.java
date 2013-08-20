package sk.stuba.fiit.perconik.debug;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;
import sk.stuba.fiit.perconik.debug.listeners.PartDebugListener;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

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
		return Strings.toStringFallback(listener);
	}

	// TODO make debug listener provider with these classes
	
	static final Set<Class<? extends Listener>> getListenerClasses()
	{
		@SuppressWarnings("unchecked")
		Class<? extends Listener>[] classes = new Class[] {
//			CommandChangeDebugListener.class,
//			CommandExecutionDebugListener.class,
//			CommandManagerChangeDebugListener.class,
//			CompletionDebugListener.class,
//			DebugEventsDebugListener.class,
//			DocumentChangeDebugListener.class,
//			FileBufferDebugListener.class,
//			JavaElementChangeDebugListener.class,
//			LaunchDebugListener.class,
//			LaunchesDebugListener.class,
//			LaunchConfigurationDebugListener.class,
//			OperationHistoryDebugListener.class,
//			PageDebugListener.class,
			PartDebugListener.class,
//			PerspectiveDebugListener.class,
//			RefactoringExecutionDebugListener.class,
//			RefactoringHistoryDebugListener.class,
//			ResourceChangeDebugListener.class,
//			SelectionDebugListener.class,
//			TestRunDebugListener.class,
//			WindowDebugListener.class,
//			WorkbenchDebugListener.class
		};
		
		return ImmutableSet.copyOf(classes);
	}
	
	// TODO mv
	public static final ListenerProvider getListenerProvider()
	{
		ListenerProvider.Builder builder = ListenerProviders.builder();
		
		builder.addAll(getListenerClasses());
		
		return builder.build();
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

	static final String dumpRegistrations()
	{
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.appendln("Registered resource to listeners map:").tab();
		
		Multimap<Resource<?>, Listener> map = Listeners.registrations();

		SortedSet<Resource<?>> resources = Sets.newTreeSet(Strings.toStringComparator());
		
		resources.addAll(map.keySet());
		
		if (!map.isEmpty())
		{
			for (Resource<?> resource: resources)
			{
				builder.appendln(DebugResources.toString(resource)).tab();
	
				List<Listener> listeners = Lists.newArrayList(map.get(resource));
				
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
