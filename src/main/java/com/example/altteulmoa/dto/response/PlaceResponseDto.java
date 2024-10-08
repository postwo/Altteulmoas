package com.example.altteulmoa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponseDto {
    private String name;        // 장소 이름
    private String address;     // 장소 주소
    private double latitude;     // 위도
    private double longitude;    // 경도
    private String phoneNumber;  // 전화번호 (선택 사항)
    private String category;     // 카테고리 (선택 사항)

}
