package sk.stuba.fiit.perconik.data.type.content;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

public final class PackageVersion implements Versioned {
  public final static Version VERSION = VersionUtil.parseVersion("1.0.0", PackageVersion.class.getPackage().getName(), "perconik-content");

  public PackageVersion() {}

  @Override
  public Version version() {
    return VERSION;
  }
}
