package com.gratex.perconik.services.uaca.ide2;

public enum IdeProjectEventType {
  SWITCH_TO("switchto"),

  ADD("add"),

  REMOVE("remove"),

  RENAME("rename"),

  OPEN("open"),

  CLOSE("close"),

  REFRESH("refresh");

  private final String urlPath;

  private IdeProjectEventType(String urlPath) {
    this.urlPath = urlPath;
  }

  public String urlPath() {
    return this.urlPath;
  }

  public static IdeProjectEventType fromValue(String urlPath) {
    for (IdeProjectEventType item: IdeProjectEventType.values()) {
      if (item.urlPath.equals(urlPath)) {
        return item;
      }
    }

    throw new IllegalArgumentException(urlPath);
  }
}
