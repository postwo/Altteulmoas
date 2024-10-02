package com.example.altteulmoa.dto.response.auth;


import com.example.altteulmoa.common.error.ErrorCode;
import com.example.altteulmoa.dto.response.ResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class SignInResponseDto extends ResponseDto {

    @JsonProperty("token")//JSON 응답에서 token 필드를 포함
    private String token; // 토큰

    @JsonProperty("expirationTime")
    private int expirationTime; // 만료시간

    public SignInResponseDto( Integer httpStatusCode, Integer errorCode, String description, String token) {
        super(httpStatusCode, errorCode, description);
        this.token = token;
        this.expirationTime = 3600; // 만료시간 1시간으로 설정
    }

    // 성공
    public static ResponseEntity<SignInResponseDto> success(String token) {
        ErrorCode errorCode = ErrorCode.SUCCESS;
        SignInResponseDto result = new SignInResponseDto(
                errorCode.getHttpStatusCode(),
                errorCode.getErrorCode(),
                errorCode.getDescription(),
                token // token 값이 제대로 설정되도록 확인
        );
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 로그인 실패
    public static ResponseEntity<ResponseDto> signFail() {
        ErrorCode errorCode = ErrorCode.SIGN_IN_FAIL;
        ResponseDto result = new ResponseDto(
                errorCode.getHttpStatusCode(),
                errorCode.getErrorCode(),
                errorCode.getDescription()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
