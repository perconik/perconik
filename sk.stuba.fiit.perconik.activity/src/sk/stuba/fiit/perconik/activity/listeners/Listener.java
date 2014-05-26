package sk.stuba.fiit.perconik.activity.listeners;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.Adapter;

/**
 * TODO
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class Listener extends Adapter
{
	private static final Executor executor = Executors.newCachedThreadPool();

	Listener()
	{
	}
	
	static final long currentTime()
	{
		return System.currentTimeMillis();
	}

	static final void execute(final Runnable command)
	{
		executor.execute(command);
	}

	static final void executeSafely(final Runnable command)
	{
		Display.getDefault().asyncExec(command);
	}
	
	// TODO
	
	// build data
	// index data
}
