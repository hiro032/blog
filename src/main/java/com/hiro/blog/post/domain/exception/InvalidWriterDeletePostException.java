package com.hiro.blog.post.domain.exception;

import com.hiro.blog.global.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidWriterDeletePostException extends BusinessException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;
    private static final String MESSAGE = "작성자만 게시물을 삭제할 수 있습니다.";

    public InvalidWriterDeletePostException() {
        super(HTTP_STATUS, MESSAGE);
    }
}
