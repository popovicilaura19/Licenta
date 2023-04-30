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

    public  static Occupation getOccupation(String fromString) {
        for (Occupation occupation : Occupation.values()
        ) {
            if (occupation.occupation.equals(fromString)) {
                return occupation;
            }
        }
        return null;
    }

}


