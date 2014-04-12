package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

public final class ListenerProviderFactory implements sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory
{
	private static final Set<Class<? extends Listener>> classes;
	
	static
	{
		Builder<Class<? extends Listener>> builder = ImmutableSet.builder();
		
		builder.add(LaunchListener.class);
		builder.add(PerspectiveListener.class);
		builder.add(SelectionListener.class);
		builder.add(WindowListener.class);
		builder.add(WorkbenchListener.class);

		classes = builder.build();
	}
	
	public ListenerProviderFactory()
	{
	}

	public final ListenerProvider create(final ListenerProvider parent)
	{
		return ListenerProviders.builder().addAll(classes).parent(parent).build();
	}
}
