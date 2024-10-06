package com.example.altteulmoa.controller;


import com.example.altteulmoa.dto.request.address.AddressRequestDto;
import com.example.altteulmoa.dto.request.auth.SignInRequestDto;
import com.example.altteulmoa.dto.request.auth.SignUpRequestDto;
import com.example.altteulmoa.dto.response.address.AddressResponseDto;
import com.example.altteulmoa.dto.response.auth.SignInResponseDto;
import com.example.altteulmoa.dto.response.auth.SignUpResponseDto;
import com.example.altteulmoa.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    //로그인
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestBody){
        ResponseEntity<? super SignInResponseDto> response = userService.login(requestBody);
        return response;
    }

    //사용자 주소 저장
    //postman으로 통과 되는데 swagger에서는 에러가 뜬다 나중에 해결하기
    @PostMapping("/address")
    public ResponseEntity<? super AddressResponseDto> address(@AuthenticationPrincipal String email, @RequestBody @Valid AddressRequestDto requestBody) {
        ResponseEntity<? super AddressResponseDto> response = userService.address(email,requestBody);
        return response;
    }


}
