package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.debug.core.ILaunch;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;

/**
 * An abstract adapter class for a {@code LaunchesListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code LaunchesListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see LaunchesListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class LaunchesAdapter extends Adapter implements LaunchesListener {
  /**
   * Constructor for use by subclasses.
   */
  protected LaunchesAdapter() {}

  public void launchesAdded(final ILaunch[] launches) {}

  public void launchesRemoved(final ILaunch[] launches) {}

  public void launchesChanged(final ILaunch[] launches) {}

  public void launchesTerminated(final ILaunch[] launches) {}
}
