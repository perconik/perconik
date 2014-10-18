package sk.stuba.fiit.perconik.data.type.content;

import com.fasterxml.jackson.databind.module.SimpleModule;

public final class ContentModule extends SimpleModule {
  private static final long serialVersionUID = 0L;

  public ContentModule() {
    super(PackageVersion.VERSION);
  }
}
