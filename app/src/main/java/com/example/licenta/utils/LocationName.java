package com.example.licenta.utils;

public enum LocationName {
    VICTORIEI_PLAZA("Victoriei Plaza"),
    STRADA_AMZEI("Strada Amzei"),
    AUREL_VLAICU("Aurel Vlaicu"),
    GROZAVESTI("Grozavesti");

    private String locationName;

    private LocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return locationName;
    }

    public static LocationName getName(String fromString) {
        for (LocationName name : LocationName.values()
        ) {
            if (name.locationName.equals(fromString)) {
                return name;
            }
        }
        return null;
    }
}
