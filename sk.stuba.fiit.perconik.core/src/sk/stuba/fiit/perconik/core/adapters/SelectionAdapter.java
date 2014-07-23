package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;

/**
 * An abstract adapter class for a {@code SelectionListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code SelectionListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see SelectionListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class SelectionAdapter extends Adapter implements SelectionListener {
  /**
   * Constructor for use by subclasses.
   */
  protected SelectionAdapter() {}

  public void selectionChanged(IWorkbenchPart part, ISelection selection) {}
}
