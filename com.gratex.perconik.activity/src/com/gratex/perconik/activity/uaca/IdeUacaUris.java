package com.gratex.perconik.activity.uaca;

import javax.ws.rs.core.UriBuilder;

public final class IdeUacaUris {
  private static final String application = "eclipse";

  private final static String base = "http://perconik.gratex.com/useractivity/enum";

  private final static UriBuilder codeElementTypeUri = UriBuilder.fromPath(base).path("idecodeelementevent/codeelementtype").fragment("{type}");

  private final static UriBuilder stateChangeTypeUri = UriBuilder.fromPath(base).path("idestatechangeevent/statetype/{idename}").fragment("{type}");

  private final static UriBuilder lookinTypeUri = UriBuilder.fromPath(base).path("idefindevent/lookintype/{idename}").fragment("{type}");

  private final static UriBuilder patternSyntaxTypeUri = UriBuilder.fromPath(base).path("idefindevent/patternsyntaxtype/{idename}").fragment("{type}");

  private final static UriBuilder rcsServerTypeUri = UriBuilder.fromPath(base).path("rcsserver/type").fragment("{type}");

  private IdeUacaUris() {
    throw new AssertionError();
  }

  public static final String forCodeElementType(final String type) {
    return codeElementTypeUri.build(type).toString();
  }

  public static final String forStateChangeType(final String type) {
    return stateChangeTypeUri.build(application, type).toString();
  }

  public static final String forLookinType(final String type) {
    return lookinTypeUri.build(application, type).toString();
  }

  public static final String forPatternSyntaxType(final String type) {
    return patternSyntaxTypeUri.build(application, type).toString();
  }

  public static final String forRcsServerType(final String type) {
    return rcsServerTypeUri.build(type).toString();
  }
}
