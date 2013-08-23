package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.ui.IEditorReference;
import sk.stuba.fiit.perconik.core.Listener;

public interface EditorListener extends Listener
{
	public void editorOpened(IEditorReference reference);

	public void editorClosed(IEditorReference reference);

	public void editorActivated(IEditorReference reference);

	public void editorDeactivated(IEditorReference reference);

	public void editorVisible(IEditorReference reference);

	public void editorHidden(IEditorReference reference);

	public void editorBroughtToTop(IEditorReference reference);

	public void editorInputChanged(IEditorReference reference);
}
