package com.castlabs.task.mp4analyzer.model;

public enum BoxType {
    MOOF("MOOF"),
    CTYP("CTYP"),
    TRAF("TRAF"),
    MFHD("MFHD"),
    TRUN("TRUN"),
    UUID("UUID"),
    MDAT("MDAT");

    private final String type;

    BoxType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static BoxType fromString(String text) {
        for (BoxType b : BoxType.values()) {
            if (b.type.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}