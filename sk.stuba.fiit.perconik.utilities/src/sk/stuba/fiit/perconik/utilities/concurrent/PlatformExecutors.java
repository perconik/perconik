package sk.stuba.fiit.perconik.utilities.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.round;
import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.defaultThreadFactory;

import static com.google.common.base.Preconditions.checkArgument;

public final class PlatformExecutors {
  private static final int availableProcessorsUpperBound = 32;

  private static final float maximumPoolSizeScalingFactor = 10;

  private static final int minimumPoolSize = 2;

  private static final float defaultPoolSizeScalingFactor = maximumPoolSizeScalingFactor;

  private PlatformExecutors() {}

  private static int boundedAvailableProcessors() {
    return min(getRuntime().availableProcessors(), availableProcessorsUpperBound);
  }

  private static int maximumPoolSize(final float poolSizeScalingFactor) {
    return max(round(min(poolSizeScalingFactor, maximumPoolSizeScalingFactor) * boundedAvailableProcessors()), minimumPoolSize);
  }

  public static float defaultPoolSizeScalingFactor() {
    return defaultPoolSizeScalingFactor;
  }

  public static ExecutorService newLimitedThreadPool() {
    return newLimitedThreadPool(maximumPoolSizeScalingFactor);
  }

  public static ExecutorService newLimitedThreadPool(final float poolSizeScalingFactor) {
    return newLimitedThreadPool(poolSizeScalingFactor, defaultThreadFactory());
  }

  public static ExecutorService newLimitedThreadPool(final float poolSizeScalingFactor, final ThreadFactory factory) {
    checkArgument(poolSizeScalingFactor >= 0);

    return new ThreadPoolExecutor(0, maximumPoolSize(poolSizeScalingFactor), 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), factory);
  }
}
