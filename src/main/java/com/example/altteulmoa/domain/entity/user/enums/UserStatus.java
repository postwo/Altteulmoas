package com.example.altteulmoa.domain.entity.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {

    ROLE_USER("일반 회원"),
    ROLE_ADMIN("관리자"),
    ROLE_MASTER("최고 관리자")
    ;

    private final String description;



}
