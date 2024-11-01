package com.example.altteulmoa.controller;

import com.example.altteulmoa.dto.response.address.SafePlaceFindResponseDto;
import com.example.altteulmoa.service.safeplace.SafePlaceFinderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/safe")
@RequiredArgsConstructor
@Slf4j
public class SafeAddressController {

    private final SafePlaceFinderService safePlaceFinderService;

    // 사용자 주소지 기반으로 장소를 추천 사용하고 싶지 않으면 선택을 안하고
    @GetMapping("/api/safe-places")
    public ResponseEntity<? super SafePlaceFindResponseDto> findSafePlaces(@AuthenticationPrincipal String email) {
        ResponseEntity<? super SafePlaceFindResponseDto> response = safePlaceFinderService.findSafeLocations(email);
        return response;
    }
}
