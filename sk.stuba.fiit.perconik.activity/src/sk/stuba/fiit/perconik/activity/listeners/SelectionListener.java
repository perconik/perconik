package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;

/**
 * TODO
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Unsupported
@Version("0.0.1")
public final class SelectionListener extends Listener implements sk.stuba.fiit.perconik.core.listeners.SelectionListener
{
	public SelectionListener()
	{
	}

	public void selectionChanged(final IWorkbenchPart part, final ISelection selection)
	{
	}
}
