package com.raonpark.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.raonpark.jpa.entity.member.GeneralUser;
import com.raonpark.jpa.entity.member.Member;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void memberTest() {
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
        memberRepository.save(user);
        Optional<Member> resultUser = memberRepository.findById(1L);

        // then
        assertEquals(resultUser.get().getEmail(), "hello@abc.net");
    }
}
