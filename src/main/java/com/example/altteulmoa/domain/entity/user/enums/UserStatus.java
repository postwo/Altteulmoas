package com.example.altteulmoa.domain.entity.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {

    USER("일반 회원"),
    ADMIN("관리자"),
    ;

    private final String description;



}
