package sk.stuba.fiit.perconik.core.debug.services.resources;

import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManagers;

public final class DebugResourceManagers {
  private DebugResourceManagers() {}

  public static DebugResourceManager create() {
    ResourceManager manager = ResourceManagers.create();

    return DebugResourceManagerProxy.wrap(manager);
  }
}
