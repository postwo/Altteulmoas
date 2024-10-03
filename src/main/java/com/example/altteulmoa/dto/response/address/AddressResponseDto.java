package com.example.altteulmoa.dto.response.address;

import com.example.altteulmoa.common.error.ErrorCode;
import com.example.altteulmoa.dto.response.ResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class AddressResponseDto extends ResponseDto {

    @JsonProperty("address")
    private String address;

    public AddressResponseDto(Integer httpStatusCode, Integer errorCode, String description,String address) {
        super(httpStatusCode, errorCode, description);
        this.address = address;
    }

    //성공
    public static ResponseEntity<AddressResponseDto> success(String address){
        ErrorCode errorCode = ErrorCode.SUCCESS;
        AddressResponseDto result = new AddressResponseDto(errorCode.getHttpStatusCode(), errorCode.getErrorCode(), errorCode.getDescription(),address);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //실패
    public static ResponseEntity<ResponseDto> notUser() {
        ErrorCode errorCode = ErrorCode.NOT_EXISTED_USER;
        ResponseDto result = new ResponseDto(
                errorCode.getHttpStatusCode(),
                errorCode.getErrorCode(),
                errorCode.getDescription()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
