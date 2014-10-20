package com.gratex.perconik.services.uaca.ide;

public enum IdeProjectEventType {
  SWITCH_TO("switchto"),

  ADD("add"),

  REMOVE("remove"),

  RENAME("rename"),

  OPEN("open"),

  CLOSE("close"),

  REFRESH("refresh");

  private final String urlPath;

  private IdeProjectEventType(final String urlPath) {
    this.urlPath = urlPath;
  }

  public String urlPath() {
    return this.urlPath;
  }

  public static IdeProjectEventType fromValue(final String urlPath) {
    for (IdeProjectEventType item: IdeProjectEventType.values()) {
      if (item.urlPath.equals(urlPath)) {
        return item;
      }
    }

    throw new IllegalArgumentException(urlPath);
  }
}
