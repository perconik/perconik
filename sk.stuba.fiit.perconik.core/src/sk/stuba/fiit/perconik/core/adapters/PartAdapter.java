package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.PartListener;

/**
 * An abstract adapter class for a {@code PartListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code PartListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see PartListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class PartAdapter extends Adapter implements PartListener {
  /**
   * Constructor for use by subclasses.
   */
  protected PartAdapter() {}

  public void partOpened(final IWorkbenchPartReference reference) {}

  public void partClosed(final IWorkbenchPartReference reference) {}

  public void partActivated(final IWorkbenchPartReference reference) {}

  public void partDeactivated(final IWorkbenchPartReference reference) {}

  public void partVisible(final IWorkbenchPartReference reference) {}

  public void partHidden(final IWorkbenchPartReference reference) {}

  public void partBroughtToTop(final IWorkbenchPartReference reference) {}

  public void partInputChanged(final IWorkbenchPartReference reference) {}
}
