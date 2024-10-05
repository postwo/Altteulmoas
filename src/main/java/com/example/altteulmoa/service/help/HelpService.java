package com.example.altteulmoa.service;

import com.example.altteulmoa.domain.entity.user.UserEntity;
import com.example.altteulmoa.dto.request.help.FindEmailRequestDto;
import com.example.altteulmoa.dto.response.help.FindEmailResponseDto;
import com.example.altteulmoa.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelpService {

    private final UserRepository userRepository;

    //이메일 찾기
    public ResponseEntity<? super FindEmailResponseDto> findEM(@Valid FindEmailRequestDto dto) {

        try{
            String telNumber = dto.getTelNumber();
            Optional<UserEntity> userEntity = userRepository.findByTelNumber(telNumber);
            if (userEntity.isEmpty()) return FindEmailResponseDto.existedUser();

            String email = userEntity.get().getEmail();

            return FindEmailResponseDto.success(email);

        }catch (Exception e){
            e.printStackTrace();
            return FindEmailResponseDto.databaseError();
        }


    }
}
