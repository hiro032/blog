package com.hiro.blog.global;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(final String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
