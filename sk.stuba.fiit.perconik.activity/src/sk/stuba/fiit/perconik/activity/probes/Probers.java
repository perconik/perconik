package sk.stuba.fiit.perconik.activity.probes;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import com.google.common.collect.ImmutableMap;

import sk.stuba.fiit.perconik.data.content.AnyContent;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.ImmutableMap.copyOf;
import static com.google.common.collect.Lists.newLinkedList;

import static sk.stuba.fiit.perconik.utilities.MoreThrowables.initializeSuppressor;

public final class Probers {
  private Probers() {}

  private static abstract class ImmutableProper<T extends AnyContent, P extends Probe<?>> extends AbstractProber<T, P> {
    final ImmutableMap<String, P> probes;

    ImmutableProper(final Map<String, P> probes) {
      this.probes = copyOf(probes);
    }

    public final Map<String, P> probes() {
      return this.probes;
    }
  }

  private static final class RegularProber<T extends AnyContent, P extends Probe<?>> extends ImmutableProper<T, P> {
    RegularProber(final Map<String, P> probes) {
      super(probes);
    }
  }

  private static final class ConcurrentProber<T extends AnyContent, P extends Probe<?>> extends ImmutableProper<T, P> {
    final ExecutorService executor;

    ConcurrentProber(final Map<String, P> probes, final ExecutorService executor) {
      super(probes);

      this.executor = requireNonNull(executor);
    }

    @Override
    public void inject(final T content) {
      final CountDownLatch latch = new CountDownLatch(this.probes.size());

      final List<RuntimeException> failures = newLinkedList();

      for (final Entry<String, P> entry: this.probes.entrySet()) {
        this.executor.execute(new Runnable() {
          public void run() {
            try {
              probeAndPut(entry.getValue(), entry.getKey(), content);
            } catch (ProbingException failure) {
              failures.add(failure);
            } finally {
              latch.countDown();
            }
          }
        });
      }

      try {
        latch.await();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      if (!failures.isEmpty()) {
        throw initializeSuppressor(new ProbingException(), failures);
      }
    }
  }

  public static <T extends AnyContent, P extends Probe<?>> Prober<T, P> create(final Map<String, P> probes) {
    return new RegularProber<>(probes);
  }

  public static <T extends AnyContent, P extends Probe<?>> Prober<T, P> create(final Map<String, P> probes, final ExecutorService executor) {
    return new ConcurrentProber<>(probes, executor);
  }
}
