package sk.stuba.fiit.perconik.activity.data.base;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Nullable;

// TODO mv to data plugin

// TODO provide checked variants of these methods: url(...)
// TODO provide optional but checked variants
// TODO provide toString(...) variants

public final class Utilities {
  private Utilities() {}

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

  public static URI uriOrNull(@Nullable final File value) {
    try {
      return value.toURI();
    } catch (Exception e) {
      return null;
    }
  }

  public static URI uriOrNull(@Nullable final Path value) {
    try {
      return value.toUri();
    } catch (Exception e) {
      return null;
    }
  }

  public static URI uriOrNull(@Nullable final String value) {
    try {
      return new URI(value);
    } catch (Exception e) {
      return null;
    }
  }

  public static URI uriOrNull(@Nullable final URL value) {
    try {
      return value.toURI();
    } catch (Exception e) {
      return null;
    }
  }

  public static URL urlOrNull(@Nullable final File value) {
    try {
      return value.toURI().toURL();
    } catch (Exception e) {
      return null;
    }
  }

  public static URL urlOrNull(@Nullable final Path value) {
    try {
      return value.toUri().toURL();
    } catch (Exception e) {
      return null;
    }
  }

  public static URL urlOrNull(@Nullable final String value) {
    try {
      return new URL(value);
    } catch (Exception e) {
      return null;
    }
  }

  public static URL urlOrNull(@Nullable final URI value) {
    try {
      return value.toURL();
    } catch (Exception e) {
      return null;
    }
  }
}
