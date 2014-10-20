package sk.stuba.fiit.perconik.activity.data.eclipse;

import java.net.URL;

import javax.annotation.Nullable;

import org.eclipse.core.runtime.IPath;
import org.eclipse.osgi.service.datalocation.Location;

// TODO add more Eclipse related conversion methods, make public then?

final class Conversions {
  private Conversions() {}

  public static URL urlOrNull(@Nullable final Location value) {
    return value != null ? value.getURL() : null;
  }

  public static URL urlOrNull(@Nullable final IPath value) {
    return sk.stuba.fiit.perconik.utilities.net.Conversions.urlOrNull(value.toFile());
  }
}
