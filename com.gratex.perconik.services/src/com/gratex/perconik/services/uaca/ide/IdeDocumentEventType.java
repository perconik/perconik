package com.gratex.perconik.services.uaca.ide;

public enum IdeDocumentEventType {
  SWITCH_TO("switchto"),

  ADD("add"),

  OPEN("open"),

  CLOSE("close"),

  REMOVE("remove"),

  SAVE("save"),

  RENAME("rename");

  private final String urlPath;

  private IdeDocumentEventType(final String urlPath) {
    this.urlPath = urlPath;
  }

  public String urlPath() {
    return this.urlPath;
  }

  public static IdeDocumentEventType fromValue(final String urlPath) {
    for (IdeDocumentEventType item: IdeDocumentEventType.values()) {
      if (item.urlPath.equals(urlPath)) {
        return item;
      }
    }

    throw new IllegalArgumentException(urlPath);
  }
}
