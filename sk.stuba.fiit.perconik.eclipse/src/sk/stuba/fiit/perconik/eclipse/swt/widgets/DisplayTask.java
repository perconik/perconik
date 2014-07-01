package sk.stuba.fiit.perconik.eclipse.swt.widgets;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import sk.stuba.fiit.perconik.utilities.concurrent.Executables;
import com.google.common.base.Supplier;

public abstract class DisplayTask<V> implements Callable<V>
{
	protected DisplayTask()
	{
	}

	public static final <V> DisplayTask<V> of(final Callable<V> callable)
	{
		return new DisplayTask<V>()
		{
			@Override
			public final V call() throws Exception
			{
				return callable.call();
			}
		};
	}

	public static final <V> DisplayTask<V> of(final Runnable runnable, final V result)
	{
		return new DisplayTask<V>()
		{
			@Override
			public final V call()
			{
				runnable.run();
				
				return result;
			}
		};
	}

	public static final <V> DisplayTask<V> of(final Supplier<V> supplier)
	{
		return new DisplayTask<V>()
		{
			@Override
			public final V call()
			{
				return supplier.get();
			}
		};
	}
	
	public final V get(final Executor executor)
	{
		return Executables.call(executor, this);
	}
}
