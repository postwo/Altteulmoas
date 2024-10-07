package com.example.altteulmoa.controller;

import com.example.altteulmoa.dto.request.address.AddressRequestDto;
import com.example.altteulmoa.dto.response.address.AddressResponseDto;
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
@RequestMapping("/address")
@RequiredArgsConstructor
@Slf4j
public class AddressController {

    private final UserService userService;

    //사용자 주소 저장
    //postman으로 통과 되는데 swagger에서는 에러가 뜬다 나중에 해결하기
    @PostMapping("/addressSave")
    public ResponseEntity<? super AddressResponseDto> address(@AuthenticationPrincipal String email, @RequestBody @Valid AddressRequestDto requestBody) {
        log.info(email);
        ResponseEntity<? super AddressResponseDto> response = userService.address(email,requestBody);
        return response;
    }
}
