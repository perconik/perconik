package sk.stuba.fiit.perconik.activity.ide;

import java.util.Set;
import sk.stuba.fiit.perconik.activity.ide.listeners.IdeSelectionListener;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

public final class IdeListenerProviderFactory implements ListenerProviderFactory
{
	private static final Set<Class<? extends Listener>> classes;
	
	static
	{
		Builder<Class<? extends Listener>> builder = ImmutableSet.builder();
		
		builder.add(IdeSelectionListener.class);

		classes = builder.build();
	}
	
	public IdeListenerProviderFactory()
	{
	}

	public final ListenerProvider create(final ListenerProvider parent)
	{
		return ListenerProviders.builder().addAll(classes).parent(parent).build();
	}
}
