package com.example.altteulmoa.repository;

import com.example.altteulmoa.domain.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    // 이메일 중복 검사
    boolean existsByEmail(String email);

    // 닉네임 중복 검사
    boolean existsByNickname(String nickname);

    //이메일 찾기
    Optional<UserEntity> findByTelNumber(String telNumber);

}
