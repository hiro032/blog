package com.hiro.blog.global.error;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;

@Getter
public class ErrorResponse {

    private final String message;
    private final List<FieldError> fieldErrors;

    private ErrorResponse (final String message, final List<FieldError> fieldErrors) {
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public static ErrorResponse fromFieldErrors(final String message, final List<FieldError> fieldErrors) {
        return new ErrorResponse(message, fieldErrors);
    }

    public static ErrorResponse fromMessage(String message) {
        return new ErrorResponse(message, Collections.emptyList());
    }

}
