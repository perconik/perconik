package com.gratex.perconik.activity.ide.listeners;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;

public final class IdeListenerProviderFactory implements ListenerProviderFactory {
  private static final Set<Class<? extends Listener>> classes;

  static {
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

  public IdeListenerProviderFactory() {}

  public ListenerProvider create(final ListenerProvider parent) {
    return ListenerProviders.builder(parent).addAll(classes).build();
  }
}
