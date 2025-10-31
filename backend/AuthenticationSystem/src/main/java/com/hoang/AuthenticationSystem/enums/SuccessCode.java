package com.hoang.AuthenticationSystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    REGISTER("AU1000", "Registration successful", HttpStatus.CREATED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
