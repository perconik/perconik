package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.debug.core.ILaunchConfiguration;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.LaunchConfigurationListener;

/**
 * An abstract adapter class for a {@code LaunchConfigurationListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 *
 * <p>Extend this class to create a {@code LaunchConfigurationListener}
 * and override the methods for the events of interest.
 *
 * @see Adapter
 * @see LaunchConfigurationListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class LaunchConfigurationAdapter extends Adapter implements LaunchConfigurationListener {
  /**
   * Constructor for use by subclasses.
   */
  protected LaunchConfigurationAdapter() {}

  public void launchConfigurationAdded(final ILaunchConfiguration configuration) {}

  public void launchConfigurationRemoved(final ILaunchConfiguration configuration) {}

  public void launchConfigurationChanged(final ILaunchConfiguration configuration) {}
}
