package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ui.IViewReference;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.ViewListener;

/**
 * An abstract adapter class for a {@code ViewListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code ViewListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see ViewListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class ViewAdapter extends Adapter implements ViewListener {
  /**
   * Constructor for use by subclasses.
   */
  protected ViewAdapter() {}

  public void viewOpened(final IViewReference reference) {}

  public void viewClosed(final IViewReference reference) {}

  public void viewActivated(final IViewReference reference) {}

  public void viewDeactivated(final IViewReference reference) {}

  public void viewVisible(final IViewReference reference) {}

  public void viewHidden(final IViewReference reference) {}

  public void viewBroughtToTop(final IViewReference reference) {}

  public void viewInputChanged(final IViewReference reference) {}
}
