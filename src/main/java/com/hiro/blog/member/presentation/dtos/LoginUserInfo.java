package com.hiro.blog.member.presentation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginUserInfo {
    private UUID id;
    private String name;
}
