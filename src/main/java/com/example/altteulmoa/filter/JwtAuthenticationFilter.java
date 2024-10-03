package com.example.altteulmoa.filter;

import com.example.altteulmoa.provider.JwtProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { //jwt 필터

    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try{
            String token =  parseBearToken(request);

            if (token == null){
                filterChain.doFilter(request,response);
                return;
            }

            String email = jwtProvider.validate(token);

            if (email ==null){
                filterChain.doFilter(request,response);
                return;
            }


            // JWT에서 권한 클레임을 가져옵니다.
            String role = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtProvider.getSecretKey().getBytes(StandardCharsets.UTF_8))) // secretKey를 JwtProvider에서 가져옵니다.
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("role", String.class); // 클레임에서 역할 가져오기

            // 복수 권한 설정 (예: ROLE_USER, ROLE_ADMIN)
            List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);

            UserDetailsImpl userDetails = new UserDetailsImpl(email, null, authorities);

            //AuthorityUtils.NO_AUTHORITIES 이거는 권한이 없다는 뜻이다
            AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, authorities);


            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);

            SecurityContextHolder.setContext(securityContext);
        }catch (Exception e){
            e.printStackTrace();
        }

        filterChain.doFilter(request,response); //다음 필터로 넘긴다는 뜻이다


    }


    private String parseBearToken(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");

        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;

        String token = authorization.substring(7); //7번째 부터 가지고 온다
        return token;
    }


}
