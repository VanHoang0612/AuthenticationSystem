package com.hoang.AuthenticationSystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    REGISTER("Registration successful", HttpStatus.CREATED),
    VERIFY_EMAIL("Email verification successful", HttpStatus.OK),
    RESEND_VERIFICATION_CODE("Verification code has been resent via email", HttpStatus.OK),
    ;
    private final String message;
    private final HttpStatus httpStatus;
}
