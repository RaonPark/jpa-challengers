package com.raonpark.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raonpark.jpa.dto.LoginDTO;
import com.raonpark.jpa.entity.member.Member;
import com.raonpark.jpa.jwt.JwtTokenProvider;
import com.raonpark.jpa.repository.MemberRepository;
import com.raonpark.jpa.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDTO loginInfo) {
        Member member = memberRepository.findByEmailAndPassword(loginInfo.getEmail(), loginInfo.getPassword());
        
        if(member != null) {
            return jwtTokenProvider.generateToken(member);
        }
        
        return "";
    }

    @Override
    public void register(Member member) {
        memberRepository.save(member);
    }
}
