package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.ListenerUnregistrationException;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Sets.newIdentityHashSet;

final class StandardListenerManager extends AbstractListenerManager {
  StandardListenerManager() {}

  @Override
  protected ResourceManager underlyingResourceManager() {
    return Services.getResourceService().getResourceManager();
  }

  public <L extends Listener> void unregisterAll(final Class<L> type) {
    List<Exception> failures = newLinkedList();

    Set<L> processed = newIdentityHashSet();

    for (Resource<? extends L> resource: this.underlyingResourceManager().assignables(type)) {
      for (L listener: resource.registered(type)) {
        try {
          if (processed.add(listener)) {
            this.unregister(listener);
          } else {
            // TODO probably unsafe
            Resource.class.cast(resource).unregister(listener);
          }
        } catch (Exception failure) {
          failures.add(failure);
        }
      }
    }

    if (!failures.isEmpty()) {
      throw MoreThrowables.initializeSuppressor(new ListenerUnregistrationException(), failures);
    }
  }

  public SetMultimap<Resource<?>, Listener> registrations() {
    SetMultimap<Resource<?>, Listener> registrations = HashMultimap.create();

    for (Resource<?> resource: this.underlyingResourceManager().assignables(Listener.class)) {
      registrations.putAll(resource, resource.registered(Listener.class));
    }

    return registrations;
  }

  public <L extends Listener> Collection<L> registered(final Class<L> type) {
    List<L> listeners = newArrayList();

    for (Resource<? extends L> resource: this.underlyingResourceManager().assignables(type)) {
      listeners.addAll(resource.registered(type));
    }

    return listeners;
  }

  public boolean registered(final Listener listener) {
    return this.registrations().containsValue(listener);
  }
}
