package com.hoang.AuthenticationSystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER("500", "Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_EXISTED("U101", "Username not existed!", HttpStatus.NOT_FOUND),
    USER_EXISTED("U102", "Username existed!", HttpStatus.CONFLICT),
    USER_DUPLICATE("U103", "User information already exists", HttpStatus.CONFLICT),
    USER_IS_ENABLED("U104", "User already verified", HttpStatus.CONFLICT),
    EMAIL_EXISTED("E101", "The email has already been used!", HttpStatus.CONFLICT),
    EMAIL_NOT_EXISTED("E102", "The email not existed!", HttpStatus.NOT_FOUND),
    VALIDATION_FAILED("VAL101", "Information validation failed!", HttpStatus.BAD_REQUEST),
    VERIFICATION_CODE_EXPIRED("VC101", "Verification code expired!", HttpStatus.BAD_REQUEST),
    VERIFICATION_CODE_INVALID("vc102", "Verification code invalid!", HttpStatus.BAD_REQUEST),
    ;
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
