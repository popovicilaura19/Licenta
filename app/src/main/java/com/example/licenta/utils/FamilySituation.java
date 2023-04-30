package com.example.licenta.utils;

public enum FamilySituation {
    MARRIED("Married"),
    NOT_MARRIED("Not Married"),
    DIVORCED("Divorced"),
    WIDOW("Widow");

    private String familySituation;

    private FamilySituation(String familySituation) {
        this.familySituation = familySituation;
    }

    @Override
    public String toString() {
        return familySituation;
    }

    public static FamilySituation getSituation(String fromString) {
        for (FamilySituation situation : FamilySituation.values()
        ) {
            if (situation.familySituation.equals(fromString)) {
                return situation;
            }
        }
        return null;
    }
}
