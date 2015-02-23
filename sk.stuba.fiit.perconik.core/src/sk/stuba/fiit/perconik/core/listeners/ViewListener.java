package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.ui.IViewReference;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A view listener.
 *
 * @see Listener
 * @see PartListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ViewListener extends Listener {
  public void viewOpened(IViewReference reference);

  public void viewClosed(IViewReference reference);

  public void viewActivated(IViewReference reference);

  public void viewDeactivated(IViewReference reference);

  public void viewVisible(IViewReference reference);

  public void viewHidden(IViewReference reference);

  public void viewBroughtToTop(IViewReference reference);

  public void viewInputChanged(IViewReference reference);
}
