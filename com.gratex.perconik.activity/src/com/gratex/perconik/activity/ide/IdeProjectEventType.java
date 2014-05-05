package com.gratex.perconik.activity.ide;

public enum IdeProjectEventType {

    SWITCH_TO("switchto"),
    ADD("add"),
    REMOVE("remove");
    
    private final String urlPath;

    IdeProjectEventType(String urlPath) {
        this.urlPath = urlPath;
    }

    public String urlPath() {
        return urlPath;
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
