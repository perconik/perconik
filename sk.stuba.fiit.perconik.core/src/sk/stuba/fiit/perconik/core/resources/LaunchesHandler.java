package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.debug.core.DebugPlugin;

import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;

enum LaunchesHandler implements Handler<LaunchesListener> {
  INSTANCE;

  public final void register(final LaunchesListener listener) {
    DebugPlugin.getDefault().getLaunchManager().addLaunchListener(listener);
  }

  public final void unregister(final LaunchesListener listener) {
    DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(listener);
  }
}
