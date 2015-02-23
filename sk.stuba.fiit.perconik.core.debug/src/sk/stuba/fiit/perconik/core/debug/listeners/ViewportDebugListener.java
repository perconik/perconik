package sk.stuba.fiit.perconik.core.debug.listeners;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.ViewportListener;

public final class ViewportDebugListener extends AbstractDebugListener implements ViewportListener {
  public ViewportDebugListener() {}

  public ViewportDebugListener(final DebugConsole console) {
    super(console);
  }

  public void viewportChanged(final int verticalOffset) {
    this.printHeader("Viewport changed");
    this.put(Debug.dumpLine("vertical offset", verticalOffset));
  }
}
