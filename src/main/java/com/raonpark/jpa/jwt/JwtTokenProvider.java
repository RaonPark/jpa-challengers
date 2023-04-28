package com.raonpark.jpa.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.raonpark.jpa.entity.member.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@PropertySource(value = "classpath:/application.properties")
public class JwtTokenProvider {
    @Value(value = "${jwt.secret.key}")
    private String jwtSecretKey;
    private long validateDateInMilliSeconds = 60 * 60 * 1000;
    private Key key;

    @PostConstruct
    public void generateKey() {
        byte[] byteKey = Base64.getDecoder().decode(jwtSecretKey);
        key = Keys.hmacShaKeyFor(byteKey);
    }

    public String generateToken(Member member) {
        Date now = new Date();
        Date validateDate = new Date(now.getTime() + validateDateInMilliSeconds);

        return Jwts.builder()
            .setSubject(member.getEmail())
            .claim("type", member.getMemberType())
            .claim("name", member.getUsername())
            .setExpiration(validateDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key)
                .build().parseClaimsJws(token);
            return jws.getBody().getExpiration().before(new Date());
        } catch(SecurityException | MalformedJwtException e) {
            log.info("JWT 서명이 잘못되었습니다.");
        } catch(IllegalArgumentException e) {
            log.info("잘못된 아규먼트가 들어왔습니다.");
        } catch(UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        }
        return false;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            log.info("잘못된 jwt 토큰입니다.");
            claims = null;
        }

        return claims;
    }
}
