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
}
