package com.gratex.perconik.uaca.data;

import java.net.URI;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUri;

public class GenericUacaEventConstants {
  /**
   * UACA event type URI prefix, value of {@code http://perconik.gratex.com/useractivity/event}
   */
  public static final URI EVENT_TYPE_URI_PREFIX = newUri("http://perconik.gratex.com/useractivity/event");

  /**
   * UACA event timestamp pattern, value of {@code yyyy-MM-dd'T'HH:mm:ss.SSSXXX}
   */
  public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

  private GenericUacaEventConstants() {}
}
