package sk.stuba.fiit.perconik.eclipse.swt.widgets;

import java.util.concurrent.Callable;
import sk.stuba.fiit.perconik.utilities.concurrent.Executables;

public abstract class DisplayTask<V> implements Callable<V>
{
	protected DisplayTask()
	{
	}
	
	public abstract V call() throws Exception;
	
	public final V get(final DisplayExecutor executor)
	{
		return Executables.call(executor, this);
	}
}
