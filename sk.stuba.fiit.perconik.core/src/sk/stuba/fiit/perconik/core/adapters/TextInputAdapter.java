package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jface.text.IDocument;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.TextInputListener;

/**
 * An abstract adapter class for a {@code TextInputListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code TextInputListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see TextInputListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class TextInputAdapter extends Adapter implements TextInputListener {
  /**
   * Constructor for use by subclasses.
   */
  protected TextInputAdapter() {}

  public void inputDocumentAboutToBeChanged(final IDocument before, final IDocument after) {}

  public void inputDocumentChanged(final IDocument before, final IDocument after) {}
}
