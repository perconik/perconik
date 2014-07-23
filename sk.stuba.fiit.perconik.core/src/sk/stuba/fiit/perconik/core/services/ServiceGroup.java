package sk.stuba.fiit.perconik.core.services;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Service.State;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.asList;
import static com.google.common.collect.Lists.newArrayList;

/**
 * An immutable set of {@code Service} instances user-specified
 * iteration order. Does not permit {@code null} elements. 
 * 
 * @param <S> the common supertype that all services must share
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ServiceGroup<S extends Service> extends ForwardingSet<S> {
  private final Set<S> services;

  ServiceGroup(final Iterable<S> services) {
    this.services = ImmutableSet.copyOf(services);
  }

  /**
   * Returns the empty service group.
   */
  public static final <S extends Service> ServiceGroup<S> of() {
    return new ServiceGroup<>(ImmutableSet.<S>of());
  }

  /**
   * Returns a service group containing a single service.
   */
  public static final <S extends Service> ServiceGroup<S> of(final S service) {
    return new ServiceGroup<>(ImmutableSet.of(service));
  }

  /**
   * Returns a service group containing the given services, in order.
   * Repeated occurrences of a service (according to {@link Object#equals})
   * after the first are ignored.
   * @throws NullPointerException if any service is {@code null}
   */
  public static final <S extends Service> ServiceGroup<S> of(final S first, final S second) {
    return new ServiceGroup<>(ImmutableSet.of(first, second));
  }

  /**
   * Returns a service group containing the given services, in order.
   * Repeated occurrences of a service (according to {@link Object#equals})
   * after the first are ignored.
   * @throws NullPointerException if any service is {@code null}
   */
  @SafeVarargs
  public static final <S extends Service> ServiceGroup<S> of(final S first, final S second, final S ... rest) {
    return new ServiceGroup<>(asList(first, second, rest));
  }

  // TODO add 4 copyOf methods like in ImmutableSet

  @Override
  protected final Set<S> delegate() {
    return this.services;
  }

  public final void startSynchronously() {
    for (S service: this.services) {
      service.startAsync();
      service.awaitRunning();
    }
  }

  public final void stopSynchronously() {
    for (S service: this.services) {
      service.stopAsync();
      service.awaitTerminated();
    }
  }

  public final ServiceGroup<S> startAsynchronously() {
    for (S service: this.services) {
      service.startAsync();
    }

    return this;
  }

  public final ServiceGroup<S> stopAsynchronously() {
    for (S service: this.services) {
      service.stopAsync();
    }

    return this;
  }

  public final void awaitRunning() {
    for (S service: this.services) {
      service.awaitRunning();
    }
  }

  public final void awaitTerminated() {
    for (S service: this.services) {
      service.awaitTerminated();
    }
  }

  public final Map<S, State> states() {
    ImmutableMap.Builder<S, State> builder = ImmutableMap.builder();

    for (S service: this.services) {
      builder.put(service, service.state());
    }

    return builder.build();
  }

  public final <U extends S> ServiceGroup<U> narrow(final Class<U> type) {
    ImmutableSet.Builder<U> builder = ImmutableSet.builder();

    for (S service: this.services) {
      if (type.isInstance(service)) {
        builder.add(type.cast(service));
      }
    }

    return new ServiceGroup<>(builder.build());
  }

  public final <U extends S> U fetch(final Class<U> type) {
    Iterator<U> iterator = this.narrow(type).iterator();

    U service = iterator.next();

    checkArgument(!iterator.hasNext());

    return service;
  }

  public final ServiceGroup<S> reverse() {
    return new ServiceGroup<>(Lists.reverse(newArrayList(this.services)));
  }
}
