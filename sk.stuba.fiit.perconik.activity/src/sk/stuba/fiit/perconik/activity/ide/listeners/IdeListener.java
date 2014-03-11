package sk.stuba.fiit.perconik.activity.ide.listeners;

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
public abstract class IdeListener extends Adapter
{
	private static final Executor executor = Executors.newCachedThreadPool();

	IdeListener()
	{
	}
	
	static final void execute(final Runnable command)
	{
		executor.execute(command);
	}

	static final void executeSafely(final Runnable command)
	{
		Display.getDefault().asyncExec(command);
	}
}
