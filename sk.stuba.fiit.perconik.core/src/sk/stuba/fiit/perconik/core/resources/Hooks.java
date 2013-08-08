package sk.stuba.fiit.perconik.core.resources;

import java.util.Arrays;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

final class Hooks
{
	private Hooks()
	{
		throw new AssertionError();
	}
	
	static final void addWindowsAsynchronouslyTo(final InternalHook<IWorkbenchWindow, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				hook.addAll(Arrays.asList(Workbenches.waitForWorkbench().getWorkbenchWindows()));
			}
		};
		
		Display.getDefault().asyncExec(initializer);
	}
	
	static final void addPagesAsynchronouslyTo(final InternalHook<IWorkbenchPage, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				hook.addAll(Arrays.asList(Workbenches.waitForActiveWindow().getPages()));
			}
		};
		
		Display.getDefault().asyncExec(initializer);
	}
}
