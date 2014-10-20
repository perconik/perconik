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

  private IdeUacaUris() {}

  public static String forCodeElementType(final String type) {
    return codeElementTypeUri.build(type).toString();
  }

  public static String forStateChangeType(final String type) {
    return stateChangeTypeUri.build(application, type).toString();
  }

  public static String forLookinType(final String type) {
    return lookinTypeUri.build(application, type).toString();
  }

  public static String forPatternSyntaxType(final String type) {
    return patternSyntaxTypeUri.build(application, type).toString();
  }

  public static String forRcsServerType(final String type) {
    return rcsServerTypeUri.build(type).toString();
  }
}
