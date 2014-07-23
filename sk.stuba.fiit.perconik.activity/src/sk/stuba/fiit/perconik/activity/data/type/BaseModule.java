package sk.stuba.fiit.perconik.activity.data.type;

import java.nio.file.Path;

import org.osgi.framework.Version;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public final class BaseModule extends SimpleModule {
  private static final long serialVersionUID = 1L;

  public BaseModule() {
    super(PackageVersion.VERSION);

    this.addSerializer(Path.class, ToStringSerializer.instance);
    this.addSerializer(Version.class, ToStringSerializer.instance);

    this.addDeserializer(Path.class, new PathDeserializer());
    this.addDeserializer(Version.class, new VersionDeserializer());
  }

  @Override
  public final boolean equals(Object o) {
    return this == o;
  }

  @Override
  public final int hashCode() {
    return BaseModule.class.hashCode();
  }
}
