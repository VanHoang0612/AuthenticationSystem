package com.hoang.AuthenticationSystem.controller;

import com.hoang.AuthenticationSystem.dto.api.ApiResponse;
import com.hoang.AuthenticationSystem.dto.auth.ReSendVerificationCodeRequest;
import com.hoang.AuthenticationSystem.dto.auth.RegisterRequest;
import com.hoang.AuthenticationSystem.dto.auth.VerifyEmailRequest;
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
        authService.register(registerRequest);
        return ResponseEntity.status(SuccessCode.REGISTER.getHttpStatus())
                .body(ApiResponse.success(null, SuccessCode.REGISTER));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<ApiResponse<?>> verifyEmail(@Valid @RequestBody VerifyEmailRequest verifyEmailRequest) {
        authService.verifyEmail(verifyEmailRequest);
        return ResponseEntity.status(SuccessCode.VERIFY_EMAIL.getHttpStatus())
                .body(ApiResponse.success(null, SuccessCode.VERIFY_EMAIL));
    }

    @PostMapping("/resend-code")
    public ResponseEntity<ApiResponse<?>> resendVerificationCode(@Valid @RequestBody ReSendVerificationCodeRequest request) {
        authService.reSendVerificationCode(request);
        return ResponseEntity.status(SuccessCode.RESEND_VERIFICATION_CODE.getHttpStatus())
                .body(ApiResponse.success(null, SuccessCode.RESEND_VERIFICATION_CODE));
    }
}
