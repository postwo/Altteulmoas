package com.example.altteulmoa.repository;

import com.example.altteulmoa.domain.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    // 이메일 중복 검사
    boolean existsByEmail(String email);

    // 닉네임 중복 검사
    boolean existsByNickname(String nickname);

}
