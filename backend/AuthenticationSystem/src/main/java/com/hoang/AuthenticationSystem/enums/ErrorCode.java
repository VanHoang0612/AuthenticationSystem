package com.hoang.AuthenticationSystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER("500", "Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_EXISTED("U1001", "Username not existed!", HttpStatus.NOT_FOUND),
    USER_EXISTED("U1002", "Username existed!", HttpStatus.CONFLICT),
    USER_DUPLICATE("U1003", "User information already exists", HttpStatus.CONFLICT),
    EMAIL_EXISTED("E1001", "The email has already been used!", HttpStatus.CONFLICT),
    VALIDATION_FAILED("VAL1", "Information validation failed!", HttpStatus.BAD_REQUEST);
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
