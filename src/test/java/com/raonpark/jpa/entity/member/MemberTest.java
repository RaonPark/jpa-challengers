package com.raonpark.jpa.entity.member;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
public class MemberTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void close() {
        factory.close();
    }

    @Test
    void member_validation_test() {
        // given
        Seller seller = new Seller();
        seller.setId(1L);
        seller.setAddress("서울특별시 용산구 가동");
        seller.setCreatedDate(new Date());
        seller.setEmail("yongsan@ssd.jfk");
        seller.setIP_address("1.2.3.4");
        seller.setMemberType(MemberType.SELLER);
        seller.setPassword("Hello123!!@");
        seller.setPhoneNumber("010-88888-28374");
        seller.setUsername("김지운");
        seller.setCompanyName("여기다");

        GeneralUser user = new GeneralUser();
        user.setId(1L);
        user.setPassword("abcdefg123");
        user.setEmail("hello@abc.net");
        user.setBirthDay(java.sql.Date.valueOf("1997-11-31"));
        user.setCreatedDate(java.sql.Date.valueOf(LocalDate.now()));
        user.setIP_address("1.0.0.1");
        user.setAddress("서울특별시 경장구 도시동");
        user.setPhoneNumber("010-88882-03891");

        // when
        Set<ConstraintViolation<Seller> > violations1 = validator.validate(seller);
        Set<ConstraintViolation<GeneralUser> > violations2 = validator.validate(user);

        // then
        assertTrue(violations1.isEmpty());
        assertFalse(violations2.isEmpty());
        violations2.forEach(violation -> {
            System.out.println(violation.getMessage());
        });
    }
}
