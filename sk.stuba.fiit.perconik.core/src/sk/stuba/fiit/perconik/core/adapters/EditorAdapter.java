package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;

/**
 * An abstract adapter class for a {@code EditorListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code EditorListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see EditorListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class EditorAdapter extends Adapter implements EditorListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected EditorAdapter()
	{
	}

	public void editorOpened(IEditorReference reference)
	{
	}

	public void editorClosed(IEditorReference reference)
	{
	}

	public void editorActivated(IEditorReference reference)
	{
	}

	public void editorDeactivated(IEditorReference reference)
	{
	}

	public void editorVisible(IEditorReference reference)
	{
	}

	public void editorHidden(IEditorReference reference)
	{
	}

	public void editorBroughtToTop(IEditorReference reference)
	{
	}

	public void editorInputChanged(IEditorReference reference)
	{
	}
}
