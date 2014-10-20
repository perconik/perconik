package sk.stuba.fiit.perconik.utilities.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.min;

public final class PlatformExecutors {
  private static final int availableProcessorsUpperBound = 32;

  private static final float maximumPoolSizeScalingFactor = 10;

  private PlatformExecutors() {}

  private static int boundedAvailableProcessors() {
    return min(Runtime.getRuntime().availableProcessors(), availableProcessorsUpperBound);
  }

  private static int maximumPoolSize() {
    return maximumPoolSize(maximumPoolSizeScalingFactor);
  }

  private static int maximumPoolSize(final float factor) {
    return Math.round(factor * boundedAvailableProcessors());
  }

  public static ExecutorService newLimitedThreadPool() {
    return newLimitedThreadPool(Executors.defaultThreadFactory());
  }

  public static ExecutorService newLimitedThreadPool(final ThreadFactory factory) {
    return new ThreadPoolExecutor(0, maximumPoolSize(), 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), factory);
  }
}
