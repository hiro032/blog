package com.hiro.blog.member.application.dtos;

import com.hiro.blog.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberCommand {


    @Getter
    @AllArgsConstructor
    public static class CreateMemberCommand {
        private String name;
        private String email;
        private String password;

        public Member toEntity() {
            return new Member(name, email, password);
        }
    }
}
