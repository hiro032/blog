package com.hiro.blog.global;

import com.hiro.blog.global.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handlerBindException(final BindException e) {
        ErrorResponse errorResponse = ErrorResponse.fromFieldErrors(e.getMessage(), e.getFieldErrors());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(final BusinessException e) {
        ErrorResponse errorResponse = ErrorResponse.fromMessage(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
    }
}
