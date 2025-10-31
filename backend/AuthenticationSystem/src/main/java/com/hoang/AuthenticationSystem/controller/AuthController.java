package com.hoang.AuthenticationSystem.controller;

import com.hoang.AuthenticationSystem.dto.api.ApiResponse;
import com.hoang.AuthenticationSystem.dto.auth.RegisterRequest;
import com.hoang.AuthenticationSystem.enums.SuccessCode;
import com.hoang.AuthenticationSystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        SuccessCode successCode = authService.register(registerRequest);
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(
                        ApiResponse.builder()
                                .code(successCode.getCode())
                                .message(successCode.getMessage())
                                .build()
                );
    }
}
