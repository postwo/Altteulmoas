package com.example.altteulmoa.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${spring.jwt.secret-key}")
    private String secretKey;

    public String create(String email) {

            Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); //만료시간

            //주입받은 비밀 키를 사용하여 HMAC SHA 알고리즘용 키 객체를 생성
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            String jwt = Jwts.builder()
                    .signWith(key, SignatureAlgorithm.HS256)
                    .setSubject(email) //JWT의 subject(주제) 필드에 사용자 email을 설정
                    .setIssuedAt(new Date()).setExpiration(expiredDate)  //JWT가 발행된 시간을 현재 시간으로 설정
                    .compact(); //JWT 문자열을 생성
            return jwt;
        }

        //jwt 검증
        public String validate(String jwt) {
            String subject = null;
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            try{
                subject = Jwts.parserBuilder()
                        .setSigningKey(key) // JWT의 서명을 검증하기 위한 키를 설정
                        .build()
                        .parseClaimsJws(jwt) //주어진 JWT를 파싱하고 서명을 검증
                        .getBody()
                        .getSubject(); //JWT의 페이로드에서 subject를 추출하여 변수에 저장

            }catch (Exception exception){
                exception.printStackTrace();
                return null; //실패하면 null
            }

            return subject;
        }

    }

