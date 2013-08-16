package sk.stuba.fiit.perconik.debug;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.debug.listeners.LaunchesDebugListener;
import sk.stuba.fiit.perconik.debug.plugin.Activator;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public final class DebugListeners
{
	private DebugListeners()
	{
		throw new AssertionError();
	}
	
	public static final void registerAll()
	{
		DebugConsole console = Debug.getDefaultConsole();
		
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
		Listeners.register(new LaunchesDebugListener(console));
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

	public static final String toString(final Class<? extends Listener> type)
	{
		return type.getName();
	}
	
	public static final String toString(final Listener listener)
	{
		return Strings.toStringFallback(listener);
	}

	// TODO
//	public static final Set<Class<? extends DebugListener>> getListenerClasses()
//	{
//		@SuppressWarnings("unchecked")
//		Class<? extends DebugListener>[] classes = new Class[] {
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
//			PartDebugListener.class,
//			PerspectiveDebugListener.class,
//			RefactoringExecutionDebugListener.class,
//			RefactoringHistoryDebugListener.class,
//			ResourceChangeDebugListener.class,
//			SelectionDebugListener.class,
//			TestRunDebugListener.class,
//			WindowDebugListener.class,
//			WorkbenchDebugListener.class
//		};
//	}

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
