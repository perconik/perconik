package com.gratex.perconik.services.uaca.ide.type;

public enum IdeDocumentEventType {

    SWITCH_TO("switchto"),
    ADD("add"),
    OPEN("open"),
    CLOSE("close"),
    REMOVE("remove"),
    SAVE("save"),
    RENAME("rename");
    
    private final String urlPath;

    IdeDocumentEventType(String urlPath) {
        this.urlPath = urlPath;
    }

    public String urlPath() {
        return urlPath;
    }

    public static IdeDocumentEventType fromValue(String urlPath) {
        for (IdeDocumentEventType item: IdeDocumentEventType.values()) {
            if (item.urlPath.equals(urlPath)) {
                return item;
            }
        }
        throw new IllegalArgumentException(urlPath);
    }
}
