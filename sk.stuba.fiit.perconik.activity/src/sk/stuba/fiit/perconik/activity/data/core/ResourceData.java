package sk.stuba.fiit.perconik.activity.data.core;

import sk.stuba.fiit.perconik.activity.data.base.NameableBaseData;
import sk.stuba.fiit.perconik.core.Resource;

public class ResourceData extends NameableBaseData {
  public ResourceData() {}

  protected ResourceData(Resource<?> resource) {
    super(resource);
  }

  public static ResourceData of(Resource<?> resource) {
    return new ResourceData(resource);
  }
}
