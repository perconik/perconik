package sk.stuba.fiit.perconik.activity.debug.listeners;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

import sk.stuba.fiit.perconik.activity.debug.LifecycleListener;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;

public final class ListenerProviderExtension implements ListenerProviderFactory {
  private static final Set<Class<? extends Listener>> classes;

  static {
    Builder<Class<? extends Listener>> builder = ImmutableSet.builder();

    builder.add(LifecycleListener.class);

    classes = builder.build();
  }

  public ListenerProviderExtension() {}

  public ListenerProvider create(final ListenerProvider parent) {
    return ListenerProviders.builder(parent).addAll(classes).build();
  }
}
