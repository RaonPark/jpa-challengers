package com.raonpark.jpa.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.raonpark.jpa.dto.LoginDTO;
import com.raonpark.jpa.entity.member.GeneralUser;
import com.raonpark.jpa.jwt.JwtTokenProvider;
import com.raonpark.jpa.repository.MemberRepository;
import com.raonpark.jpa.service.impl.MemberServiceImpl;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 나머지를 Mock으로 하고 InjectMocks를 사용하면 PasswordEncoder가 실제로 동작하지
    // 않으므로 password.encode()가 null을 반환하게 된다. 따라서 꼭 생성자로 객체로 만들자.
    private MemberServiceImpl memberService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        memberService = new MemberServiceImpl(memberRepository, jwtTokenProvider, passwordEncoder);
    }

    @Test
    public void 로그인테스트() {
        // given
        GeneralUser user = new GeneralUser();
        user.setId(1L);
        user.setPassword("Abcdefg123!");
        user.setEmail("hello@abc.net");
        user.setBirthDay(Date.valueOf("1997-11-31"));
        user.setCreatedDate(Date.valueOf(LocalDate.now()));
        user.setIP_address("1.0.0.1");
        user.setAddress("서울특별시 경장구 도시동");
        user.setPhoneNumber("010-88882-03891");

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("hello@abc.net");
        loginDTO.setPassword("Abcdefg123!");

        // when
        memberService.register(user);
        String jwtToken = memberService.login(loginDTO);

        // then
        assertTrue(jwtTokenProvider.validateToken(jwtToken), "Hello!");
    }
}
