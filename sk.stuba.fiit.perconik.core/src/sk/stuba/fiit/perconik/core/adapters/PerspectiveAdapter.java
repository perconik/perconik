package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;

/**
 * An abstract adapter class for a {@code PerspectiveListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code PerspectiveListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see PerspectiveListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class PerspectiveAdapter extends Adapter implements PerspectiveListener {
  /**
   * Constructor for use by subclasses.
   */
  protected PerspectiveAdapter() {}

  public void perspectiveOpened(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectiveClosed(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectiveDeactivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectivePreDeactivate(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final String change) {}

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final IWorkbenchPartReference reference, final String change) {}

  public void perspectiveSavedAs(final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {}
}
