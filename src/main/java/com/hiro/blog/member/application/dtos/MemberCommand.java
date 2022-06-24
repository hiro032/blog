package com.hiro.blog.member.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberCommand {

    @Getter
    @AllArgsConstructor
    public static class CreateMemberCommand {
        private String name;
        private String username;
        private String password;
    }
}
