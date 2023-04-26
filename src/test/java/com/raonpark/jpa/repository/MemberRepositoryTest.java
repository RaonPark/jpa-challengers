package com.raonpark.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.raonpark.jpa.entity.member.Member;
import com.raonpark.jpa.entity.member.User;

@SpringBootTest
public class MemberRepositoryTest {
    @Mock
    private MemberRepository memberRepository;

    @Test
    public void memberTest() {
        // given
        User user = new User();
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
        assertTrue((resultUser.get() instanceof User) ? true : false, "hello");
        assertEquals(resultUser.get().getEmail(), "hello@abc.net");
    }
}
