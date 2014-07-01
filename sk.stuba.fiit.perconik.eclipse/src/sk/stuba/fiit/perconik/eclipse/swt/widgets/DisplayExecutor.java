package sk.stuba.fiit.perconik.eclipse.swt.widgets;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.concurrent.Executor;
import org.eclipse.swt.widgets.Display;

public abstract class DisplayExecutor implements Executor
{
	final Display display;
	
	DisplayExecutor(final Display display)
	{
		this.display = checkNotNull(display);
	}

	public static final DisplayExecutor defaultAsynchronous()
	{
		return createAsynchronous(Display.getDefault());
	}
	
	public static final DisplayExecutor defaultSynchronous()
	{
		return createSynchronous(Display.getDefault());
	}

	public static final DisplayExecutor currentAsynchronous()
	{
		return createAsynchronous(Display.getCurrent());
	}

	public static final DisplayExecutor currentSynchronous()
	{
		return createSynchronous(Display.getCurrent());
	}

	public static final DisplayExecutor createAsynchronous(final Display display)
	{
		return new Asynchronous(display);
	}

	public static final DisplayExecutor createSynchronous(final Display display)
	{
		return new Synchronous(display);
	}

	private static final class Asynchronous extends DisplayExecutor
	{
		Asynchronous(final Display display)
		{
			super(display);
		}
		
		@Override
		public final void execute(Runnable command)
		{
			this.display.asyncExec(command);
		}
	}
	
	private static final class Synchronous extends DisplayExecutor
	{
		Synchronous(final Display display)
		{
			super(display);
		}
		
		@Override
		public final void execute(Runnable command)
		{
			this.display.syncExec(command);
		}
	}

	public abstract void execute(Runnable command);

	public final Display getDisplay()
	{
		return this.display;
	}
}
