package com.gratex.perconik.activity;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.gratex.perconik.activity.listeners.IdeCodeListener;
import com.gratex.perconik.activity.listeners.IdeCommitListener;
import com.gratex.perconik.activity.listeners.IdeDocumentListener;
import com.gratex.perconik.activity.listeners.IdeElementListener;
import com.gratex.perconik.activity.listeners.IdeFindListener;
import com.gratex.perconik.activity.listeners.IdeProjectListener;
import com.gratex.perconik.activity.listeners.IdeStateListener;

public final class ActivityListenerProviderFactory implements ListenerProviderFactory
{
	private static final Set<Class<? extends Listener>> classes;
	
	static
	{
		Builder<Class<? extends Listener>> builder = ImmutableSet.builder();
		
		builder.add(IdeCodeListener.class);
		builder.add(IdeCommitListener.class);
		builder.add(IdeDocumentListener.class);
		builder.add(IdeElementListener.class);
		builder.add(IdeFindListener.class);
		builder.add(IdeProjectListener.class);
		builder.add(IdeStateListener.class);

		classes = builder.build();
	}
	
	public ActivityListenerProviderFactory()
	{
	}

	public final ListenerProvider create(final ListenerProvider parent)
	{
		return ListenerProviders.builder().addAll(classes).parent(parent).build();
	}
}
