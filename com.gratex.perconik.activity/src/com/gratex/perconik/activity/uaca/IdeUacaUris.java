package com.gratex.perconik.activity.uaca;

import javax.ws.rs.core.UriBuilder;

import com.gratex.perconik.uaca.GenericUacaEventConstants;

public final class IdeUacaUris {
  private static final String application = "eclipse";

  private final static UriBuilder baseTypeUri = UriBuilder.fromUri(GenericUacaEventConstants.EVENT_TYPE_URI_PREFIX).path("enum");

  private final static UriBuilder codeElementTypeUri = baseTypeUri.clone().path("idecodeelementevent/codeelementtype").fragment("{type}");

  private final static UriBuilder stateChangeTypeUri = baseTypeUri.clone().path("idestatechangeevent/statetype/{idename}").fragment("{type}");

  private final static UriBuilder lookinTypeUri = baseTypeUri.clone().path("idefindevent/lookintype/{idename}").fragment("{type}");

  private final static UriBuilder patternSyntaxTypeUri = baseTypeUri.clone().path("idefindevent/patternsyntaxtype/{idename}").fragment("{type}");

  private final static UriBuilder rcsServerTypeUri = baseTypeUri.clone().path("rcsserver/type").fragment("{type}");

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
