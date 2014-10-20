package sk.stuba.fiit.perconik.core.debug.listeners;

import java.util.EnumSet;
import java.util.Set;

import org.eclipse.core.resources.IResourceChangeEvent;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;

public final class ResourceDebugListener extends AbstractDebugListener implements ResourceListener {
  public ResourceDebugListener() {}

  public ResourceDebugListener(final DebugConsole console) {
    super(console);
  }

  public void resourceChanged(final IResourceChangeEvent event) {
    this.printHeader("Resource changed");
    this.printResourceChangeEvent(event);
  }

  public Set<ResourceEventType> getEventTypes() {
    return EnumSet.allOf(ResourceEventType.class);
  }

  private void printResourceChangeEvent(final IResourceChangeEvent event) {
    this.put(Debug.dumpResourceChangeEvent(event));
  }
}
