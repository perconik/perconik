package com.gratex.perconik.services.uaca.ide.type;

public enum IdeCodeEventType {

    SELECTION_CHANGED("selectionchanged"),
    PASTE("paste"),
    COPY("copy"),
    CUT("cut");
    
    private final String urlPath;

    IdeCodeEventType(String urlPath) {
        this.urlPath = urlPath;
    }

    public String urlPath() {
        return urlPath;
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
