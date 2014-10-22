package sk.stuba.fiit.perconik.activity.data.core;

import sk.stuba.fiit.perconik.activity.data.NameableData;
import sk.stuba.fiit.perconik.core.Resource;

public class ResourceData extends NameableData {
  public ResourceData() {}

  protected ResourceData(final Resource<?> resource) {
    super(resource);
  }

  public static ResourceData of(final Resource<?> resource) {
    return new ResourceData(resource);
  }
}
