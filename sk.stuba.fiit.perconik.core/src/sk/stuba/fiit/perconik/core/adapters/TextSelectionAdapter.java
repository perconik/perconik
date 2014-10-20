package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.TextSelectionListener;

/**
 * An abstract adapter class for a {@code TextSelectionListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code TextSelectionListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see TextSelectionListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class TextSelectionAdapter extends Adapter implements TextSelectionListener {
  /**
   * Constructor for use by subclasses.
   */
  protected TextSelectionAdapter() {}

  public void selectionChanged(final IWorkbenchPart part, final ITextSelection selection) {}
}
