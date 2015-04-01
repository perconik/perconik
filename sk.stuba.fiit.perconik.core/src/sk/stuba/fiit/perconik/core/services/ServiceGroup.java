package sk.stuba.fiit.perconik.core.services;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Service.State;

import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

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
  // TODO remove and replace it with ServiceManager or use ServiceManager internally

  private final Set<S> services;

  ServiceGroup(final Iterable<S> services) {
    this.services = ImmutableSet.copyOf(services);
  }

  /**
   * Returns the empty service group.
   */
  public static <S extends Service> ServiceGroup<S> of() {
    return new ServiceGroup<>(ImmutableSet.<S>of());
  }

  /**
   * Returns a service group containing a single service.
   */
  public static <S extends Service> ServiceGroup<S> of(final S service) {
    return new ServiceGroup<>(ImmutableSet.of(service));
  }

  /**
   * Returns a service group containing the given services, in order.
   * Repeated occurrences of a service (according to {@link Object#equals})
   * after the first are ignored.
   * @throws NullPointerException if any service is {@code null}
   */
  public static <S extends Service> ServiceGroup<S> of(final S first, final S second) {
    return new ServiceGroup<>(ImmutableSet.of(first, second));
  }

  /**
   * Returns a service group containing the given services, in order.
   * Repeated occurrences of a service (according to {@link Object#equals})
   * after the first are ignored.
   * @throws NullPointerException if any service is {@code null}
   */
  @SafeVarargs
  public static <S extends Service> ServiceGroup<S> of(final S first, final S second, final S ... rest) {
    return new ServiceGroup<>(asList(first, second, rest));
  }

  @Override
  protected Set<S> delegate() {
    return this.services;
  }

  public void startSynchronously() {
    for (S service: this.services) {
      service.startAsync();
      service.awaitRunning();
    }
  }

  public void startSynchronously(final long timeout, final TimeUnit unit) throws TimeoutException {
    long slice = NANOSECONDS.convert(timeout, unit);

    Stopwatch stopwatch = Stopwatch.createStarted();

    for (S service: this.services) {
      service.startAsync();
      service.awaitRunning(slice, NANOSECONDS);

      slice -= stopwatch.elapsed(NANOSECONDS);

      if (slice < 0) {
        throw new TimeoutException();
      }
    }
  }

  public void startSynchronously(final TimeValue timeout) throws TimeoutException {
    startSynchronously(timeout.duration(), timeout.unit());
  }

  public void stopSynchronously() {
    for (S service: this.services) {
      service.stopAsync();
      service.awaitTerminated();
    }
  }

  public void stopSynchronously(final long timeout, final TimeUnit unit) throws TimeoutException {
    long slice = NANOSECONDS.convert(timeout, unit);

    Stopwatch stopwatch = Stopwatch.createStarted();

    for (S service: this.services) {
      service.stopAsync();
      service.awaitTerminated(slice, NANOSECONDS);

      slice -= stopwatch.elapsed(NANOSECONDS);

      if (slice < 0) {
        throw new TimeoutException();
      }
    }
  }

  public void stopSynchronously(final TimeValue timeout) throws TimeoutException {
    stopSynchronously(timeout.duration(), timeout.unit());
  }

  public Map<S, State> states() {
    ImmutableMap.Builder<S, State> builder = ImmutableMap.builder();

    for (S service: this.services) {
      builder.put(service, service.state());
    }

    return builder.build();
  }

  public <U extends S> ServiceGroup<U> narrow(final Class<U> type) {
    ImmutableSet.Builder<U> builder = ImmutableSet.builder();

    for (S service: this.services) {
      if (type.isInstance(service)) {
        builder.add(type.cast(service));
      }
    }

    return new ServiceGroup<>(builder.build());
  }

  public <U extends S> U fetch(final Class<U> type) {
    Iterator<U> iterator = this.narrow(type).iterator();

    U service = iterator.next();

    checkArgument(!iterator.hasNext());

    return service;
  }

  public ServiceGroup<S> reverse() {
    return new ServiceGroup<>(Lists.reverse(newArrayList(this.services)));
  }
}
