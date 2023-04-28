package com.raonpark.jpa.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raonpark.jpa.dto.LoginDTO;
import com.raonpark.jpa.entity.member.Member;
import com.raonpark.jpa.jwt.JwtTokenProvider;
import com.raonpark.jpa.repository.MemberRepository;
import com.raonpark.jpa.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDTO loginInfo) {
        String email = loginInfo.getEmail();

        Optional<Member> member = memberRepository.findByEmail(email);
        
        if(!member.isPresent()) {
            return "";
        }

        String rawPassword = loginInfo.getPassword();
        String encodedPassword = member.get().getPassword();

        if(passwordEncoder.matches(rawPassword, encodedPassword)) {
            return jwtTokenProvider.generateToken(member.get());
        }
        
        return "";
    }

    @Override
    public void register(Member member) {
        String password = member.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);

        memberRepository.save(member);
    }
}
