package sk.stuba.fiit.perconik.data.type.common;

import java.nio.file.Path;

import org.osgi.framework.Version;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public final class CommonModule extends SimpleModule {
  private static final long serialVersionUID = 0L;

  public CommonModule() {
    super(PackageVersion.VERSION);

    this.addSerializer(Path.class, ToStringSerializer.instance);
    this.addSerializer(Version.class, ToStringSerializer.instance);

    this.addDeserializer(Path.class, new PathDeserializer());
    this.addDeserializer(Version.class, new VersionDeserializer());
  }
}
