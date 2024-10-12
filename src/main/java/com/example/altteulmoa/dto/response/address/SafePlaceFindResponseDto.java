package com.example.altteulmoa.dto.response.address;

import com.example.altteulmoa.common.error.ErrorCode;
import com.example.altteulmoa.dto.PlaceResponseDto;
import com.example.altteulmoa.dto.response.ResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class SafePlaceFindResponseDto extends ResponseDto {

    @JsonProperty("safePlaces")
    private List<PlaceResponseDto> safePlaces;

    public SafePlaceFindResponseDto(Integer httpStatusCode, Integer errorCode, String description,List<PlaceResponseDto> safePlaces) {
        super(httpStatusCode, errorCode, description);
        this.safePlaces = safePlaces;
    }

    public static ResponseEntity<SafePlaceFindResponseDto> success(List<PlaceResponseDto> safePlaces) {
        ErrorCode errorCode = ErrorCode.SUCCESS;
        SafePlaceFindResponseDto result = new SafePlaceFindResponseDto(
                errorCode.getHttpStatusCode(),
                errorCode.getErrorCode(),
                errorCode.getDescription(),
                        safePlaces
        );
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //안전한 장소가 없음
    public static ResponseEntity<ResponseDto> noSafe(){
        ErrorCode errorCode = ErrorCode.NO_SAFE_PLACES_FOUND;
        ResponseDto responseBody = new ResponseDto(
                errorCode.getHttpStatusCode(),
                errorCode.getErrorCode(),
                errorCode.getDescription()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }


}
