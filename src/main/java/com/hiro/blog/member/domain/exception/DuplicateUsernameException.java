package com.hiro.blog.member.domain.exception;

import com.hiro.blog.global.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends BusinessException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;
    private static final String MESSAGE = "이미 가입된 아이디 입니다.";

    public DuplicateUsernameException() {
        super(HTTP_STATUS, MESSAGE);
    }
}
