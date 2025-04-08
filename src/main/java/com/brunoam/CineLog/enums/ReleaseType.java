package com.brunoam.CineLog.entities;

public enum ReleaseType {
    THEATRICAL("Theatrical"),
    DIGITAL("Digital"),
    DVD("DVD"),
    BLURAY("Blu-ray"),
    VHS("VHS"),
    TV("TV"),
    STREAMING("Streaming"),
    FESTIVAL("Festival"),
    RE_RELEASE("Re-release"),
    OTHER("Other");

    private final String displayName;

    ReleaseType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
