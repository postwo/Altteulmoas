package com.example.altteulmoa.controller;

import com.example.altteulmoa.dto.request.help.FindEmailRequestDto;
import com.example.altteulmoa.dto.response.help.FindEmailResponseDto;
import com.example.altteulmoa.service.HelpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/help")
@RequiredArgsConstructor
@Slf4j
public class HelpController {

    private final HelpService helpService;

    //이메일 찾기
    @PostMapping("/find-email")
    public ResponseEntity<? super FindEmailResponseDto> findEmail(@RequestBody @Valid FindEmailRequestDto requestBody){
        ResponseEntity<? super FindEmailResponseDto> response = helpService.findEM(requestBody);
        return response;
    }

    //Todo: 비밀번호 찾기 추가할 예정
}
