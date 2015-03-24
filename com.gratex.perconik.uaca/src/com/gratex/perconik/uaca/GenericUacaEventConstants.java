package com.gratex.perconik.uaca;

import java.net.URI;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUri;

public class GenericUacaEventConstants {
  public static final URI EVENT_TYPE_URI_PREFIX = newUri("http://perconik.gratex.com/useractivity");

  public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

  private GenericUacaEventConstants() {}
}
