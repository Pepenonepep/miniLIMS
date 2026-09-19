package com.labware.minilims.domain.enums;

public enum Status {
    RECEIVED(1),
    ANALYSIS(2),
    APPROVED(3),
    REJECTED(4);

    private int code;

    private Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status toEnum(int code) {
        for (Status status : Status.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status code: " + code);
    }
}
