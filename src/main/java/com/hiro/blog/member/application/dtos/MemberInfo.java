package com.hiro.blog.member.application.dtos;

import com.hiro.blog.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
public class MemberInfo {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class CreateMemberInfo {

        private UUID id;
        private String name;
        private String email;

        public static CreateMemberInfo fromEntity(Member member) {
            return new CreateMemberInfo(member.getId(), member.getName(), member.getUsername());
        }
    }

}
