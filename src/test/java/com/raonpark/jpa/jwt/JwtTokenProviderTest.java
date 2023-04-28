package com.raonpark.jpa.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.raonpark.jpa.entity.member.GeneralUser;
import com.raonpark.jpa.repository.MemberRepository;

@SpringBootTest
public class JwtTokenProviderTest {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Mock
    private MemberRepository memberRepository;

    @Test
    public void 토큰_생성_테스트() {
        // given
        GeneralUser user = new GeneralUser();
        user.setId(1L);
        user.setPassword("abcdefg123");
        user.setEmail("hello@abc.net");
        user.setBirthDay(Date.valueOf("1997-11-31"));
        user.setCreatedDate(Date.valueOf(LocalDate.now()));
        user.setIP_address("1.0.0.1");
        user.setAddress("서울특별시 경장구 도시동");
        user.setPhoneNumber("010-88882-03891");

        // when
        String jwtToken = jwtTokenProvider.generateToken(user);

        // then
        assertNotNull(jwtToken, "no!!!");
    }

    @Test
    public void 토큰에서_아이디_추출() {
        // given
        GeneralUser user = new GeneralUser();
        user.setId(1L);
        user.setPassword("abcdefg123");
        user.setEmail("hello@abc.net");
        user.setBirthDay(Date.valueOf("1997-11-31"));
        user.setCreatedDate(Date.valueOf(LocalDate.now()));
        user.setIP_address("1.0.0.1");
        user.setAddress("서울특별시 경장구 도시동");
        user.setPhoneNumber("010-88882-03891");

        // when
        String jwtToken = jwtTokenProvider.generateToken(user);

        // then
        String email = jwtTokenProvider.getClaimsFromToken(jwtToken).getSubject();
        assertEquals(user.getEmail(), email, "email not equals");
    }
}
