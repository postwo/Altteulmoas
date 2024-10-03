package com.example.altteulmoa.common.exception;

import com.example.altteulmoa.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //특정 컨트롤러에서 발생한 예외뿐 아니라 전체 애플리케이션의 컨트롤러에서 발생하는 예외를 처리
public class badRequestExceptionHandler { //잘못된 요청과 관련된 예외를 처리한다

    //ExceptionHandler 특정 예외가 발생했을 때 해당 예외를 처리하는 메서드를 지정하는 어노테이션
    //이 코드에서는 MethodArgumentNotValidException과 HttpMessageNotReadableException이 발생했을 때, 해당 예외를 validationExceptionHandler 메서드에서 처리하도록 지정
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseDto> validationExceptionHandler(Exception e) {
        return ResponseDto.validationFailed();
    }

}
