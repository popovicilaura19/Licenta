package com.example.licenta.utils;

public enum Occupation {
    STUDENT("Student"),
    EMPLOYED("Employed"),
    UNEMPLOYED("Unemployed"),
    RETIRED("Retired");

    private String occupation;

    private Occupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return occupation;
    }

}


