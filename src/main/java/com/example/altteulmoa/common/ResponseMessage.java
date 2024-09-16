package com.example.altteulmoa.common;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    // HTTP STATUS 200
    SUCCESS("Success"),

    // HTTP STATUS 400
    VALIDATION_FAIL("Validation failed"), // 유효성 검증 실패
    DUPLICATE_EMAIL("Duplicate email"), // 중복된 이메일
    DUPLICATE_NICKNAME("Duplicate nickname"), // 중복된 닉네임
    DUPLICATE_TEL_NUMBER("Duplicate tel number"), // 중복된 전화번호
    NOT_EXISTED_USER("This user does not exist"), // 존재하지 않는 유저
    NOT_EXISTED_BOARD("This board does not exist"), // 존재하지 않는 게시물

    // HTTP STATUS 401
    SIGN_IN_FAIL("Login information mismatch"), // 로그인 실패
    AUTHORIZATION_FAIL("Authorization failed"), // 인증 실패

    // HTTP STATUS 403
    NO_PERMISSION("No permission"), // 권한 없음

    // HTTP STATUS 500
    DATABASE_ERROR("Database error"); // 데이터베이스 에러

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

}
