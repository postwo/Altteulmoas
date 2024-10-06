package com.example.altteulmoa.converter.user;

import com.example.altteulmoa.domain.entity.user.UserEntity;
import com.example.altteulmoa.dto.request.auth.SignUpRequestDto;

public class UserConverter {

    //회원가입
    public static UserEntity toEntity(SignUpRequestDto dto) {
        return UserEntity.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .userStatus(dto.getUserStatus())
                .telNumber(dto.getTelNumber()) // telNumber 추가
                .build();
    }
}
