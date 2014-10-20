package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.debug.core.DebugPlugin;

import sk.stuba.fiit.perconik.core.listeners.LaunchConfigurationListener;

enum LaunchConfigurationHandler implements Handler<LaunchConfigurationListener> {
  INSTANCE;

  public void register(final LaunchConfigurationListener listener) {
    DebugPlugin.getDefault().getLaunchManager().addLaunchConfigurationListener(listener);
  }

  public void unregister(final LaunchConfigurationListener listener) {
    DebugPlugin.getDefault().getLaunchManager().removeLaunchConfigurationListener(listener);
  }
}
