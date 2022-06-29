package com.hiro.blog.post.domain.exception;

public class InvalidWriterDeletePostException extends RuntimeException {

    private static final String MESSAGE = "작성자만 게시물을 삭제할 수 있습니다.";

    public InvalidWriterDeletePostException() {
        super(MESSAGE);
    }
}
