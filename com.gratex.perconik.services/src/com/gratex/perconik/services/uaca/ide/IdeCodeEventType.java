package com.gratex.perconik.services.uaca.ide;

public enum IdeCodeEventType {
  SELECTION_CHANGED("selectionchanged"),

  PASTE("paste"),

  COPY("copy"),

  CUT("cut");

  private final String urlPath;

  private IdeCodeEventType(String urlPath) {
    this.urlPath = urlPath;
  }

  public String urlPath() {
    return this.urlPath;
  }

  public static IdeCodeEventType fromValue(String urlPath) {
    for (IdeCodeEventType item: IdeCodeEventType.values()) {
      if (item.urlPath.equals(urlPath)) {
        return item;
      }
    }

    throw new IllegalArgumentException(urlPath);
  }
}
