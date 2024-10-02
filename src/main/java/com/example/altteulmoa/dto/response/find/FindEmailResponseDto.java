package com.example.altteulmoa.dto.response.find;

import com.example.altteulmoa.common.error.ErrorCode;
import com.example.altteulmoa.dto.response.ResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class FindEmailResponseDto extends ResponseDto {

    @JsonProperty("email")// JSON 응답에서 이메일 필드를 포함
    private String email;

    public FindEmailResponseDto(Integer httpStatusCode, Integer errorCode, String description,String email) {
        super(httpStatusCode, errorCode, description);
        this.email =email;
    }

    //성공
    public static ResponseEntity<FindEmailResponseDto> success(String email){
        ErrorCode errorCode = ErrorCode.SUCCESS;
        FindEmailResponseDto responseBody = new FindEmailResponseDto(errorCode.getHttpStatusCode(),errorCode.getErrorCode(), errorCode.getDescription(),email);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    //유저가 없다
    public static ResponseEntity<FindEmailResponseDto> existedUser(){
       ErrorCode errorCode = ErrorCode.NOT_EXISTED_USER;
        FindEmailResponseDto result = new FindEmailResponseDto(errorCode.getHttpStatusCode(), errorCode.getErrorCode(), errorCode.getDescription(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }


}
