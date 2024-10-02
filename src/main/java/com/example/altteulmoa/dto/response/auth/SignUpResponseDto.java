package com.example.altteulmoa.dto.response.auth;

import com.example.altteulmoa.common.error.ErrorCode;
import com.example.altteulmoa.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class SignUpResponseDto extends ResponseDto {


    public SignUpResponseDto(Integer httpStatusCode, Integer errorCode, String description) {
        super(httpStatusCode, errorCode, description);
    }

    //성공
    public static ResponseEntity<SignUpResponseDto> success(){
        ErrorCode errorCode = ErrorCode.SUCCESS;
        SignUpResponseDto responseBody = new SignUpResponseDto(errorCode.getHttpStatusCode(),errorCode.getErrorCode(), errorCode.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    // 공통 오류 응답
    private static ResponseEntity<SignUpResponseDto> createErrorResponse(ErrorCode errorCode) {
        SignUpResponseDto result = new SignUpResponseDto(
                errorCode.getHttpStatusCode(),
                errorCode.getErrorCode(),
                errorCode.getDescription()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<SignUpResponseDto> duplicateEmail(){
        return createErrorResponse(ErrorCode.DUPLICATE_EMAIL);
    }

    public static ResponseEntity<SignUpResponseDto> duplicateNickname(){
        return createErrorResponse(ErrorCode.DUPLICATE_NICKNAME);
    }


}
