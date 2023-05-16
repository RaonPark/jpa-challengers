package com.raonpark.jpa.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PropertySource(value = "classpath:/application.properties")
public class JwtFilter extends OncePerRequestFilter{
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.auth.header}")
    private String authHeader;

    @Value("${jwt.token.type}")
    private String tokenType;

    @Value("${jwt.secret.key}")
    private String secretKey;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String bearer = request.getHeader(authHeader);
        
        if(bearer != null) {
            String token = resolveToken(bearer);

            if(token != null && jwtTokenProvider.validateToken(token)) {
                Claims claims = jwtTokenProvider.getClaimsFromToken(token);

                String userEmail = claims.getSubject();
                if(userEmail != null) {
                    filterChain.doFilter(request, response);
                } else {
                    log.info("jwt 토큰이 잘못되었습니다.");
                }
            } else {
                log.info("jwt 토큰이 잘못되었습니다.");
            }
        } else {
            log.info("헤더가 잘못되었습니다.");
        }
    }

    private String resolveToken(String bearer) {
        if(StringUtils.hasText(bearer) && bearer.startsWith(tokenType)) {
            return bearer.substring(7);
        }
        return null;
    }
}
