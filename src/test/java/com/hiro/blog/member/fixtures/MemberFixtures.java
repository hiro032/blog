package com.hiro.blog.member.fixtures;

import com.hiro.blog.member.presentation.dtos.MemberRequest;

public class MemberFixtures {

    public static MemberRequest memberRequest() {
        return new MemberRequest("hiro", "hiro@email.com", "aA123456!");
    }
}
