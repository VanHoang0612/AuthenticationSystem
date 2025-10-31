package com.hoang.AuthenticationSystem.dto.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoang.AuthenticationSystem.enums.ErrorCode;
import com.hoang.AuthenticationSystem.enums.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String code;
    private String message;
    List<String> errors;
    T data;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(T data, SuccessCode successCode) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(successCode.getMessage())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> failure(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
