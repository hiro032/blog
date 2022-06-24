package com.hiro.blog.member.fixtures;

import com.hiro.blog.member.presentation.dtos.MemberRequest;

public class MemberFixtures {

    public static MemberRequest memberRequest() {
        return new MemberRequest("유저 이름", "유저 아이디", "aA123456!");
    }
}
