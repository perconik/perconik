package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.ui.IEditorReference;
import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;

public final class EditorDebugListener extends AbstractDebugListener implements EditorListener
{
	public EditorDebugListener()
	{
	}
	
	public EditorDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void editorOpened(final IEditorReference reference)
	{
		this.printHeader("Editor opened");
		this.printEditorReference(reference);
	}

	public final void editorClosed(final IEditorReference reference)
	{
		this.printHeader("Editor closed");
		this.printEditorReference(reference);
	}

	public final void editorActivated(final IEditorReference reference)
	{
		this.printHeader("Editor activated");
		this.printEditorReference(reference);
	}

	public final void editorDeactivated(final IEditorReference reference)
	{
		this.printHeader("Editor deactivated");
		this.printEditorReference(reference);
	}

	public final void editorVisible(final IEditorReference reference)
	{
		this.printHeader("Editor visible");
		this.printEditorReference(reference);
	}

	public final void editorHidden(final IEditorReference reference)
	{
		this.printHeader("Editor hidden");
		this.printEditorReference(reference);
	}

	public final void editorBroughtToTop(final IEditorReference reference)
	{
		this.printHeader("Editor brought to top");
		this.printEditorReference(reference);
	}

	public final void editorInputChanged(final IEditorReference reference)
	{
		this.printHeader("Editor input changed");
		this.printEditorReference(reference);
	}
	
	private final void printEditorReference(final IEditorReference reference)
	{
		this.put(Debug.dumpEditorReference(reference));
	}
}
