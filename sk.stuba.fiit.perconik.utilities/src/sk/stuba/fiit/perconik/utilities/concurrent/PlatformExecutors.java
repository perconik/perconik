package sk.stuba.fiit.perconik.utilities.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class PlatformExecutors
{
	private static final int availableProcessorsUpperBound = 32;
	
	private static final float maximumPoolSizeScalingFactor = 10;
	
	private PlatformExecutors()
	{
		throw new AssertionError();
	}

	private static final int boundedAvailableProcessors()
	{
		return Math.min(Runtime.getRuntime().availableProcessors(), availableProcessorsUpperBound);
	}

	private static final int maximumPoolSize()
	{
		return maximumPoolSize(maximumPoolSizeScalingFactor);
	}
	
	private static final int maximumPoolSize(float factor)
	{
		return Math.round(factor * boundedAvailableProcessors());
	}

	public static final ExecutorService newLimitedThreadPool()
	{
		return newLimitedThreadPool(Executors.defaultThreadFactory());
	}

	public static final ExecutorService newLimitedThreadPool(ThreadFactory factory)
	{
		return new ThreadPoolExecutor(0, maximumPoolSize(), 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), factory);
	}
}
