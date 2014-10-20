package sk.stuba.fiit.perconik.utilities.net;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

import javax.annotation.Nullable;

// TODO provide checked variants of these methods: url(...)
// TODO provide optional but checked variants
// TODO provide toString(...) variants

public final class Conversions {
  private Conversions() {}

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
