package com.example.altteulmoa.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class AuthorizeHttpRequestsException implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json"); //getWriter()를 json형태로 반환 해준다
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//권한없음
        response.getWriter().write("{\"code\" : \"AUTHORIZATION_FAIL\" ,\"message\" : \"사용자 인증 실패.\"}");

    }

}
