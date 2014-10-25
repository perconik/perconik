package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.debug.core.ILaunch;

import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;

@Unsupported
@Version("0.0.1")
public final class LaunchListener extends SharingEventListener implements sk.stuba.fiit.perconik.core.listeners.LaunchesListener {
  public LaunchListener() {}

  public final void launchesAdded(final ILaunch[] launches) {}

  public final void launchesRemoved(final ILaunch[] launches) {}

  public final void launchesChanged(final ILaunch[] launches) {}

  public final void launchesTerminated(final ILaunch[] launches) {}
}
