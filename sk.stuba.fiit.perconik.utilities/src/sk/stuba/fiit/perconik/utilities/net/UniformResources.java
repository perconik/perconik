package sk.stuba.fiit.perconik.utilities.net;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLStreamHandler;

public final class UniformResources {
  private UniformResources() {}

  public static URI newUri(final String str) {
    try {
      return new URI(str);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URI newUri(final String scheme, final String login, final String host, final int port, final String path, final String query, final String fragment) {
    try {
      return new URI(scheme, login, host, port, path, query, fragment);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URI newUri(final String scheme, final String authority, final String path, final String query, final String fragment) {
    try {
      return new URI(scheme, authority, path, query, fragment);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URI newUri(final String scheme, final String host, final String path, final String fragment) {
    try {
      return new URI(scheme, host, path, fragment);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URI newUri(final String scheme, final String part, final String fragment) {
    try {
      return new URI(scheme, part, fragment);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URL newUrl(final String content) {
    try {
      return new URL(content);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URL newUrl(final String protocol, final String host, final String file) {
    try {
      return new URL(protocol, host, file);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URL newUrl(final String protocol, final String host, final int port, final String file) {
    try {
      return new URL(protocol, host, port, file);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URL newUrl(final String protocol, final String host, final int port, final String file, final URLStreamHandler handler) {
    try {
      return new URL(protocol, host, port, file, handler);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URL newUrl(final URL context, final String content) {
    try {
      return new URL(context, content);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static URL newUrl(final URL context, final String content, final URLStreamHandler handler) {
    try {
      return new URL(context, content, handler);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
