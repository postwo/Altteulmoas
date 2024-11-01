package com.example.altteulmoa.domain.entity.grouparticle.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ArticleStatus {
    IN_PROGRESS("모집 중"),    // 진행 중인 상태
    COMPLETED("모집 완료");   // 완료된 상태
    ;

    private final String description;
}
