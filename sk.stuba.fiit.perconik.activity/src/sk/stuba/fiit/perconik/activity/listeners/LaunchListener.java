package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.debug.core.ILaunch;

import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;

/**
 * TODO
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Unsupported
@Version("0.0.1")
public final class LaunchListener extends Listener implements sk.stuba.fiit.perconik.core.listeners.LaunchesListener {
  public LaunchListener() {}

  public void launchesAdded(ILaunch[] launches) {}

  public void launchesRemoved(ILaunch[] launches) {}

  public void launchesChanged(ILaunch[] launches) {}

  public void launchesTerminated(ILaunch[] launches) {}
}
