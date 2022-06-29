package com.hiro.blog.member.domain.exception;

import com.hiro.blog.global.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    private static final String MESSAGE = "아이디에 해당하는 유저를 찾을 수 없습니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
