package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.debug.core.DebugPlugin;

import sk.stuba.fiit.perconik.core.listeners.LaunchListener;

enum LaunchHandler implements Handler<LaunchListener> {
  INSTANCE;

  public void register(final LaunchListener listener) {
    DebugPlugin.getDefault().getLaunchManager().addLaunchListener(listener);
  }

  public void unregister(final LaunchListener listener) {
    DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(listener);
  }
}
