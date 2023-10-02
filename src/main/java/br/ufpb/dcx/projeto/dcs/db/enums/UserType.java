package br.ufpb.dcx.projeto.dcs.db.enums;

public enum UserType {
    PRIVATE("Private"),
    CHURCH("Church"),
    MUNICIPAL_PUBLIC_BODY("Municipal Public Body"),
    STATE_PUBLIC_BODY("State Public Body"),
    FEDERAL_PUBLIC_BODY("Federal Public Body"),
    NGO("NGO - Non-Governmental Organization"),
    ASSOCIATION_OR_SOCIETY("Association or Society");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
