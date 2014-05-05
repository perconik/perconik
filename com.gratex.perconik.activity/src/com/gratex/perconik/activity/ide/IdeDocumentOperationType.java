package com.gratex.perconik.activity.ide;

public enum IdeDocumentOperationType {

    SWITCH_TO("switchto"),
    ADD("add"),
    OPEN("open"),
    CLOSE("close"),
    REMOVE("remove"),
    SAVE("save"),
    RENAME("rename");
    
    private final String urlPath;

    IdeDocumentOperationType(String urlPath) {
        this.urlPath = urlPath;
    }

    public String urlPath() {
        return urlPath;
    }

    public static IdeDocumentOperationType fromValue(String urlPath) {
        for (IdeDocumentOperationType item: IdeDocumentOperationType.values()) {
            if (item.urlPath.equals(urlPath)) {
                return item;
            }
        }
        throw new IllegalArgumentException(urlPath);
    }
}
