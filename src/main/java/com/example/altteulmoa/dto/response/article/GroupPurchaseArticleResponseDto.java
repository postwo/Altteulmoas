package com.example.altteulmoa.dto.response.article;

import com.example.altteulmoa.common.error.ErrorCode;
import com.example.altteulmoa.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GroupPurchaseArticleResponseDto extends ResponseDto {

    public GroupPurchaseArticleResponseDto(Integer httpStatusCode, Integer errorCode, String description) {
        super(httpStatusCode, errorCode, description);
    }

    //성공
    public static ResponseEntity<GroupPurchaseArticleResponseDto> success() {
        ErrorCode errorCode = ErrorCode.SUCCESS;
        GroupPurchaseArticleResponseDto responseBody = new GroupPurchaseArticleResponseDto(errorCode.getHttpStatusCode(), errorCode.getErrorCode(), errorCode.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    //사진 첨부 필수 에러 띄워주기





}
