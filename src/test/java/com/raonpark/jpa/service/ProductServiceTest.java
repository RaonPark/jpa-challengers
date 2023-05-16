package com.raonpark.jpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.raonpark.jpa.entity.member.MemberType;
import com.raonpark.jpa.entity.member.Seller;
import com.raonpark.jpa.entity.product.Product;
import com.raonpark.jpa.jwt.JwtTokenProvider;
import com.raonpark.jpa.repository.MemberRepository;
import com.raonpark.jpa.repository.ProductRepository;
import com.raonpark.jpa.service.impl.MemberServiceImpl;
import com.raonpark.jpa.service.impl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceTest {
    private ProductServiceImpl productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    private MemberServiceImpl memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void init() {
        memberService = new MemberServiceImpl(memberRepository, jwtTokenProvider, passwordEncoder);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void 상품_등록() {
        // given
        Product product = new Product();
        product.setId(1L);
        product.setName("Galax GTX 3060ti");
        product.setPrice(360000);
        product.setRegisteredDate(new Date());

        Seller seller = new Seller();
        seller.setCompanyName("Galax");
        seller.setId(1L);
        seller.setAddress("서울특별시 용산구 가동");
        seller.setCreatedDate(new Date());
        seller.setEmail("yongsan@ssd.jfk");
        seller.setIP_address("1.2.3.4");
        seller.setMemberType(MemberType.SELLER);
        seller.setPassword("Pro123!@#");
        seller.setPhoneNumber("010-88888-28374");
        seller.setUsername("김지운");
        
        // dirty check
        seller.getProducts().add(product);
        product.getSellers().add(seller);

        // when
        memberService.register(seller);
        productService.registerProduct(seller, product);

        // then
        Product registeredProduct = productService.getProductByName(seller, "Galax GTX 3060ti");
        assertEquals(product, registeredProduct, "not same");
    }
}
