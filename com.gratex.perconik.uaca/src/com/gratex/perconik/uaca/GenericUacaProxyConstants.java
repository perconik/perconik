package com.gratex.perconik.uaca;

import java.net.URL;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUrl;

public class GenericUacaProxyConstants {
  /**
   * UACA default local URL, value of {@code http://localhost:16375}
   */
  public static final URL DEFAULT_APPLICATION_URL = newUrl("http://localhost:16375");

  /**
   * UACA generic event path, value of {@code generic/event}
   */
  public static final String GENERIC_EVENT_PATH = "generic/event";

  private GenericUacaProxyConstants() {}
}
