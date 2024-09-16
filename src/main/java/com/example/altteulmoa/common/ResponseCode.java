package com.example.altteulmoa.common;

import lombok.Getter;

@Getter
public enum ResponseCode {

    // HTTP STATUS 200
    SUCCESS("SU"),

    // HTTP STATUS 400
    VALIDATION_FAIL("VF"),
    DUPLICATE_EMAIL("DF"),
    DUPLICATE_NICKNAME("DN"),
    DUPLICATE_TEL_NUMBER("DT"),
    NOT_EXISTED_USER("NU"),
    NOT_EXISTED_BOARD("NB"),

    // HTTP STATUS 401
    SIGN_IN_FAIL("SF"),
    AUTHORIZATION_FAIL("AF"),

    // HTTP STATUS 403
    NO_PERMISSION("NP"),

    // HTTP STATUS 500
    DATABASE_ERROR("DBE");

    private final String code;

    ResponseCode(String code) {
        this.code = code;
    }

}
