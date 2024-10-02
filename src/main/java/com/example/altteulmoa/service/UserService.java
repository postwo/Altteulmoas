package com.example.altteulmoa.service;

import com.example.altteulmoa.converter.user.UserConverter;
import com.example.altteulmoa.domain.entity.user.UserEntity;
import com.example.altteulmoa.domain.entity.user.enums.UserStatus;
import com.example.altteulmoa.dto.request.auth.SignUpRequestDto;
import com.example.altteulmoa.dto.response.auth.SignUpResponseDto;
import com.example.altteulmoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //회원가입
    public ResponseEntity<? super SignUpResponseDto> register(SignUpRequestDto dto) {

      try{
          //email 중복검사
          String email = dto.getEmail();
          boolean Duplicate_email =userRepository.existsByEmail(email);
          if (Duplicate_email) return SignUpResponseDto.duplicateEmail();

          //닉네임 중복검사
          String nickname = dto.getNickname();
          boolean Duplicate_nickname = userRepository.existsByNickname(nickname);
          if (Duplicate_nickname) return SignUpResponseDto.duplicateNickname();

          dto.setPassword(passwordEncoder.encode(dto.getPassword()));
          dto.setUserStatus(UserStatus.USER);

          UserEntity userEntity = UserConverter.toEntity(dto);

          userRepository.save(userEntity);
      }catch (Exception e){
          //에러처리
           e.printStackTrace();
           return SignUpResponseDto.databaseError();
      }

      return SignUpResponseDto.success();
    }


}
