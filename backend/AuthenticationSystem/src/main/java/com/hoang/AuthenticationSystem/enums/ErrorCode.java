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
    TOKEN_TYPE_INVALID("T101", "Token type invalid!", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED("T102", "Token expired", HttpStatus.BAD_REQUEST),
    LOGIN("A001", "The account or password is incorrect", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_INVALID("A102", "Refresh token invalid", HttpStatus.BAD_REQUEST),
    ACCESS_TOKEN_INVALID("A103", "Access token invalid", HttpStatus.BAD_REQUEST),
    NOT_FOUND_IN_COOKIES("A104", "Cookie not found in cookies", HttpStatus.BAD_REQUEST),
    TOKEN_EXISTS("A105", "Token exists", HttpStatus.CONFLICT),
    USER_DISABLED("A106", "The account not verified", HttpStatus.FORBIDDEN),
    UNAUTHORIZED("UNAUTHORIZED", "UNAUTHORIZED", HttpStatus.UNAUTHORIZED),
    ;
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
