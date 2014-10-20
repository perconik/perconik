package sk.stuba.fiit.perconik.utilities.io;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Nullable;

// TODO provide toString(...) variants

public final class Conversions {
  private Conversions() {}

  public static File fileOrNull(@Nullable final Path value) {
    try {
      return value.toFile();
    } catch (Exception e) {
      return null;
    }
  }

  public static File fileOrNull(@Nullable final String value) {
    try {
      return new File(value);
    } catch (Exception e) {
      return null;
    }
  }

  public static File fileOrNull(@Nullable final URI value) {
    try {
      return new File(value);
    } catch (Exception e) {
      return null;
    }
  }

  public static File fileOrNull(@Nullable final URL value) {
    try {
      return new File(value.toURI());
    } catch (Exception e) {
      return null;
    }
  }

  public static Path pathOrNull(@Nullable final File value) {
    try {
      return value.toPath();
    } catch (Exception e) {
      return null;
    }
  }

  public static Path pathOrNull(@Nullable final String value) {
    try {
      return Paths.get(value);
    } catch (Exception e) {
      return null;
    }
  }

  public static Path pathOrNull(@Nullable final URI value) {
    try {
      return Paths.get(value);
    } catch (Exception e) {
      return null;
    }
  }

  public static Path pathOrNull(@Nullable final URL value) {
    try {
      return Paths.get(value.toURI());
    } catch (Exception e) {
      return null;
    }
  }
}
