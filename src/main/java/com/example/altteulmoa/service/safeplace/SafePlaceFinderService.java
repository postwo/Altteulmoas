package com.example.altteulmoa.service.safeplace;

import com.example.altteulmoa.domain.entity.user.UserEntity;
import com.example.altteulmoa.dto.PlaceResponseDto;
import com.example.altteulmoa.dto.response.address.SafePlaceFindResponseDto;
import com.example.altteulmoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SafePlaceFinderService {

    @Value("${kakao.map.api.url}")
    private String kakaoMapApiUrl;

    @Value("${kakao.map.api.key}")
    private String kakaoMapApiKey;

    private final UserRepository userRepository;

    public ResponseEntity<? super SafePlaceFindResponseDto> findSafeLocations(String email) {

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if (userEntityOptional.isEmpty()) {
            return SafePlaceFindResponseDto.existedUser();
        }

        UserEntity userEntity = userEntityOptional.get();
        String address = userEntity.getAddress();

        // 주소에서 위도 및 경도 얻기
        double[] latLng = getLatLngFromAddress(address);

        if (latLng != null) {
            double latitude = latLng[0];
            double longitude = latLng[1];

            // 위도 및 경도로 안전한 장소 검색
            List<PlaceResponseDto> safePlaces = findPlacesNearby(latitude, longitude, 4000);

            // 결과 처리
            if (safePlaces.isEmpty()) {
                return SafePlaceFindResponseDto.noSafe();
            }

            return SafePlaceFindResponseDto.success(safePlaces);
        }

        return SafePlaceFindResponseDto.noSafe();
    }


    private double[] getLatLngFromAddress(String address) {

        String url = kakaoMapApiUrl + "?query=" + address;
        RestTemplate restTemplate = new RestTemplate();

        // API 호출 시 필요한 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoMapApiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // API 호출
        //여기가 문제
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        // 응답에서 위도와 경도 추출
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Map<String, Object>> documents = (List<Map<String, Object>>) response.getBody().get("documents");
            if (!documents.isEmpty()) {
                Map<String, Object> location = documents.get(0);

                // "y"와 "x"를 String으로 가져온 다음, Double로 변환
                String latStr = (String) location.get("y");
                String lonStr = (String) location.get("x");

                // 문자열을 double로 변환
                double latitude = Double.parseDouble(latStr);
                double longitude = Double.parseDouble(lonStr);

                return new double[]{latitude, longitude}; // 위도와 경도 반환
            }
        }

        return null; // 위도와 경도를 찾을 수 없는 경우 null 반환
    }


    private List<PlaceResponseDto> findPlacesNearby(double latitude, double longitude, int radius) {
        String url = kakaoMapApiUrl.replace("address.json", "keyword.json") + "?query=카페&y=" + latitude + "&x=" + longitude + "&radius=" + radius;
        RestTemplate restTemplate = new RestTemplate();

        // API 호출 시 필요한 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoMapApiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // API 호출
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        // 응답에서 장소 정보 추출
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Map<String, Object>> documents = (List<Map<String, Object>>) response.getBody().get("documents");

            // PlaceRequestDto로 변환하여 반환
            return documents.stream().map(document -> {
                        String name = (String) document.get("place_name");
                        String address = (String) document.get("road_address_name");
                        double lat = Double.parseDouble((String) document.get("y"));
                        double lng = Double.parseDouble((String) document.get("x"));
                        String phoneNumber = (String) document.get("phone");
                        String category = (String) document.get("category_name");

                        return new PlaceResponseDto(name, address, lat, lng, phoneNumber, category);
                    }).limit(5) // 최대 5개로 제한
                    .collect(Collectors.toList());
        }

        return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
    }
}

