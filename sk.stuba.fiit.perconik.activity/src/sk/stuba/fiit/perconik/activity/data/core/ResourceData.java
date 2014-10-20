package sk.stuba.fiit.perconik.activity.data.core;

import sk.stuba.fiit.perconik.activity.data.base.NameableTypeData;
import sk.stuba.fiit.perconik.core.Resource;

public class ResourceData extends NameableTypeData {
  public ResourceData() {}

  protected ResourceData(final Resource<?> resource) {
    super(resource);
  }

  public static ResourceData of(final Resource<?> resource) {
    return new ResourceData(resource);
  }
}
