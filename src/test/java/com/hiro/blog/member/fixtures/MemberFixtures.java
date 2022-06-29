package com.hiro.blog.member.fixtures;

import com.hiro.blog.member.application.dtos.MemberCommand;
import com.hiro.blog.member.domain.Member;
import com.hiro.blog.member.presentation.dtos.MemberRequest;

public class MemberFixtures {

    private static final String DEFAULT_NAME = "hiro";
    private static final String DEFAULT_USERNAME = "hiro123";
    private static final String DEFAULT_PASSWORD = "1234";

    public static MemberRequest memberRequest() {
        return new MemberRequest("my name", "my username", "1234");
    }

    public static Member member() {
        return new Member(DEFAULT_NAME, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public static Member member(final String username) {
        return new Member(DEFAULT_NAME, username, DEFAULT_PASSWORD);
    }

    public static MemberCommand.CreateMemberCommand createMemberCommand(final String username) {
        return new MemberCommand.CreateMemberCommand(DEFAULT_NAME, username, DEFAULT_PASSWORD);
    }
}
