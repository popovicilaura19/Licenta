package com.example.licenta.utils;

public enum RequestStatus {
    APPROVED("Approved"),
    REQUIRES_AGENT_REVIEW("Requires agent review"),
    REJECTED("Rejected");

    private String status;

    private RequestStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    public  static RequestStatus getStatus(String fromString) {
        for (RequestStatus status : RequestStatus.values()
        ) {
            if (status.status.equals(fromString)) {
                return status;
            }
        }
        return null;
    }
}
