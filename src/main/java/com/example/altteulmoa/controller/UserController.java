package com.example.altteulmoa.controller;


import com.example.altteulmoa.dto.request.auth.SignUpRequestDto;
import com.example.altteulmoa.dto.request.find.FindEmailRequestDto;
import com.example.altteulmoa.dto.response.auth.SignUpResponseDto;
import com.example.altteulmoa.dto.response.find.FindEmailResponseDto;
import com.example.altteulmoa.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestBody){
        ResponseEntity<? super SignUpResponseDto> response = userService.register(requestBody);
        return response;
    }


    //이메일 찾기
    @PostMapping("/find-email")
    public ResponseEntity<? super FindEmailResponseDto> findEmail(@RequestBody @Valid FindEmailRequestDto requestBody){
      ResponseEntity<? super FindEmailResponseDto> response = userService.findEM(requestBody);
      return response;
    }
}
