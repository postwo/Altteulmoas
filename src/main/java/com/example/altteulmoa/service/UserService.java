package com.example.altteulmoa.service;

import com.example.altteulmoa.converter.user.UserConverter;
import com.example.altteulmoa.domain.entity.user.UserEntity;
import com.example.altteulmoa.domain.entity.user.enums.UserStatus;
import com.example.altteulmoa.dto.request.address.AddressRequestDto;
import com.example.altteulmoa.dto.request.auth.SignInRequestDto;
import com.example.altteulmoa.dto.request.auth.SignUpRequestDto;
import com.example.altteulmoa.dto.response.address.AddressResponseDto;
import com.example.altteulmoa.dto.response.auth.SignInResponseDto;
import com.example.altteulmoa.dto.response.auth.SignUpResponseDto;
import com.example.altteulmoa.filter.UserDetailsImpl;
import com.example.altteulmoa.provider.JwtProvider;
import com.example.altteulmoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

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


    //로그인
    public ResponseEntity<? super SignInResponseDto> login(SignInRequestDto dto){

        String token = null;

        try{
            String email = dto.getEmail();
            Optional<UserEntity> userEntity = userRepository.findByEmail(email);
            if (userEntity.isEmpty()) return SignInResponseDto.signFail();

            String password = dto.getPassword();
            String encodePassword = userEntity.get().getPassword();
            boolean isMatched = passwordEncoder.matches(password,encodePassword);
            if (!isMatched) return SignInResponseDto.signFail();

            // 사용자 역할 가져오기
            String role = "ROLE_" + userEntity.get().getUserStatus().name();

            token = jwtProvider.create(email,role);

        }catch (Exception e){
            e.printStackTrace();
            return SignInResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }

    //주소 저장
    public ResponseEntity<? super AddressResponseDto> address(UserDetailsImpl userDetails, AddressRequestDto dto) {
        try {
            String email = userDetails.getUsername();
            Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);

            if (optionalUserEntity.isEmpty()) return AddressResponseDto.notUser();


            // Optional에서 UserEntity 가져오기
            UserEntity userEntity = optionalUserEntity.get();

            userEntity.setAddress(dto.getAddress());

            userRepository.save(userEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return SignInResponseDto.databaseError();
        }
        return AddressResponseDto.success(dto.getAddress());
    }

}
