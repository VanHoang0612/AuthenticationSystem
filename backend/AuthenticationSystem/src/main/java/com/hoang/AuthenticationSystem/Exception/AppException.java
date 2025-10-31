package com.hoang.AuthenticationSystem.Exception;

import com.hoang.AuthenticationSystem.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class AppException  extends RuntimeException{
    private final ErrorCode errorCode;
    private final List<String> errors;

    public AppException(ErrorCode errorCode, List<String> errors) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.errors = errors;
    }
}
