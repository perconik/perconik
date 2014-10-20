package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.debug.core.DebugPlugin;

import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;

enum LaunchesHandler implements Handler<LaunchesListener> {
  INSTANCE;

  public void register(final LaunchesListener listener) {
    DebugPlugin.getDefault().getLaunchManager().addLaunchListener(listener);
  }

  public void unregister(final LaunchesListener listener) {
    DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(listener);
  }
}
