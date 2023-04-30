package com.example.licenta.utils;

public enum CreditType {
    STUDENT_LOAN("Student Loan"),
    PERSONAL_LOAN("Personal Loan"),
    HOUSE_LOAN("House Loan"),
    TRAVEL_LOAN("Travel Loan");

    private String creditType;

    private CreditType(String creditType) {
        this.creditType = creditType;
    }

    @Override
    public String toString() {
        return creditType;
    }

    public  static CreditType getType(String fromString) {
        for (CreditType type : CreditType.values()
        ) {
            if (type.creditType.equals(fromString)) {
                return type;
            }
        }
        return null;
    }
}
