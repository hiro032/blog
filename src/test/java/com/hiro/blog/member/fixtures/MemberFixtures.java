package com.hiro.blog.member.fixtures;

import com.hiro.blog.member.presentation.dtos.MemberRequest;

public class MemberFixtures {

    public static MemberRequest memberRequest() {
        return new MemberRequest("my name", "my username", "1234");
    }
}
