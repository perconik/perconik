package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.debug.core.ILaunch;

import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class LaunchListener extends SharingEventListener implements LaunchesListener {
  public LaunchListener() {}

  public void launchesAdded(final ILaunch[] launches) {}

  public void launchesRemoved(final ILaunch[] launches) {}

  public void launchesChanged(final ILaunch[] launches) {}

  public void launchesTerminated(final ILaunch[] launches) {}
}
