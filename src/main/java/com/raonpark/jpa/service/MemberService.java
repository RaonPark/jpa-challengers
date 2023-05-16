package com.raonpark.jpa.service;

import com.raonpark.jpa.dto.LoginDTO;
import com.raonpark.jpa.entity.member.Member;

public interface MemberService {
    void register(Member member);
    String login(LoginDTO loginInfo);
}
