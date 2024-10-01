package com.example.altteulmoa.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeIfs {

    // HTTP STATUS 200
    SUCCESS(200, 200, "성공"),

    // HTTP STATUS 400
    VALIDATION_FAIL(400, 1001, "유효성 검사 실패"), // 인자 추가
    DUPLICATE_EMAIL(400, 1002, "이메일 중복"),
    DUPLICATE_NICKNAME(400, 1003, "닉네임 중복"),
    NOT_EXISTED_USER(404, 1005, "존재하지 않는 사용자"),

    // HTTP STATUS 401
    SIGN_IN_FAIL(401, 2001, "로그인 실패"),
    AUTHORIZATION_FAIL(401, 2002, "사용자 인증 실패"),

    // HTTP STATUS 403
    NO_PERMISSION(403, 3001, "권한 없음"),

    // HTTP STATUS 500
    DATABASE_ERROR(500, 4001, "데이터베이스 오류");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
