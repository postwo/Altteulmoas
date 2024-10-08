package com.example.altteulmoa.dto.response;


import com.example.altteulmoa.common.error.ErrorCode;
import com.example.altteulmoa.dto.response.help.FindEmailResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private Integer httpStatusCode;
    private Integer errorCode;
    private String description;

    // 데이터베이스 에러
    public static ResponseEntity<ResponseDto> databaseError(){
        ErrorCode errorCode = ErrorCode.DATABASE_ERROR;
        ResponseDto responseBody = new ResponseDto(errorCode.getHttpStatusCode(),errorCode.getErrorCode(), errorCode.getDescription());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    //검증 실패
    public static ResponseEntity<ResponseDto> validationFailed(){
        ErrorCode errorCode = ErrorCode.VALIDATION_FAIL;
        ResponseDto responseBody = new ResponseDto(errorCode.getHttpStatusCode(),errorCode.getErrorCode(), errorCode.getDescription());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    //사용자 정보를 찾을수 없습니다
    public static ResponseEntity<ResponseDto> existedUser(){
        ErrorCode errorCode = ErrorCode.NOT_EXISTED_USER;
        FindEmailResponseDto result = new FindEmailResponseDto(errorCode.getHttpStatusCode(), errorCode.getErrorCode(), errorCode.getDescription(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

}