package com.hiro.blog.post.domain.exception;

import com.hiro.blog.global.EntityNotFoundException;

public class PostNotFoundException extends EntityNotFoundException {

    private static final String MESSAGE = "아이디에 해당하는 Post를 찾을 수 없습니다.";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}
