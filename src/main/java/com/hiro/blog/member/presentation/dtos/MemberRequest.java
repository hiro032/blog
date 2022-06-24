package com.hiro.blog.member.presentation.dtos;

import com.hiro.blog.member.application.dtos.MemberCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public MemberCommand.CreateMemberCommand toCommand() {
        return new MemberCommand.CreateMemberCommand(name, username, password);
    }
}
