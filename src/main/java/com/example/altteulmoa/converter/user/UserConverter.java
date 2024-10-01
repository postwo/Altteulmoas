package com.example.altteulmoa.converter.user;

import com.example.altteulmoa.domain.entity.user.UserEntity;
import com.example.altteulmoa.dto.request.auth.SignUpRequestDto;

public class UserConverter {

    //회원가입
    public static UserEntity toEntity(SignUpRequestDto dto) {
        return new UserEntity(
                dto.getEmail(),
                dto.getPassword(),
                dto.getNickname(),
                dto.getUserStatus()
        );
    }
}
