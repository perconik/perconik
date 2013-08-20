package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IWorkbenchPartReference;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class PartDebugListener extends AbstractDebugListener implements PartListener
{
	public PartDebugListener()
	{
	}
	
	public PartDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void partOpened(final IWorkbenchPartReference reference)
	{
		this.printHeader("Part opened");
		this.printPartReference(reference);
	}

	public final void partClosed(final IWorkbenchPartReference reference)
	{
		this.printHeader("Part closed");
		this.printPartReference(reference);
	}

	public final void partActivated(final IWorkbenchPartReference reference)
	{
		this.printHeader("Part activated");
		this.printPartReference(reference);
	}

	public final void partDeactivated(final IWorkbenchPartReference reference)
	{
		this.printHeader("Part deactivated");
		this.printPartReference(reference);
	}

	public final void partVisible(final IWorkbenchPartReference reference)
	{
		this.printHeader("Part visible");
		this.printPartReference(reference);
	}

	public final void partHidden(final IWorkbenchPartReference reference)
	{
		this.printHeader("Part hidden");
		this.printPartReference(reference);
	}

	public final void partBroughtToTop(final IWorkbenchPartReference reference)
	{
		this.printHeader("Part brought to top");
		this.printPartReference(reference);
	}

	public final void partInputChanged(final IWorkbenchPartReference reference)
	{
		this.printHeader("Part input changed");
		this.printPartReference(reference);
	}
	
	private final void printPartReference(final IWorkbenchPartReference reference)
	{
		this.put(Debug.dumpPartReference(reference));
	}
}
