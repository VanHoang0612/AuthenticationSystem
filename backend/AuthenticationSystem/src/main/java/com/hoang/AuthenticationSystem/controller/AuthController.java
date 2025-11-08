package com.hoang.AuthenticationSystem.controller;

import com.hoang.AuthenticationSystem.dto.api.ApiResponse;
import com.hoang.AuthenticationSystem.dto.auth.*;
import com.hoang.AuthenticationSystem.enums.SuccessCode;
import com.hoang.AuthenticationSystem.service.AuthService;
import com.hoang.AuthenticationSystem.utils.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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

    @Value("${security.jwt.expiration-ms.refresh}")
    private long refreshExpirationMs;

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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        int maxAgeInSeconds = (int) (refreshExpirationMs / 1000);
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", authResponse.getRefreshToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(maxAgeInSeconds)
                .sameSite("None")
                .build();
        authResponse.setRefreshToken(null);
        return ResponseEntity.status(SuccessCode.LOGIN.getHttpStatus())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(ApiResponse.success(authResponse, SuccessCode.LOGIN));

    }

    @PostMapping("/validate-token")
    public ResponseEntity<ApiResponse<?>> validateAccessToken(@RequestBody String token) {
        authService.validateAccessToken(token.trim());
        return ResponseEntity.status(SuccessCode.VALIDATE_TOKEN.getHttpStatus())
                .body(ApiResponse.success(null, SuccessCode.VALIDATE_TOKEN));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(HttpServletRequest request) {
        String refreshToken = CookieUtils.getCookieValue(request, "refreshToken");
        return ResponseEntity.status(SuccessCode.REFRESH_TOKEN.getHttpStatus())
                .body(ApiResponse.success(authService.refreshToken(refreshToken), SuccessCode.REFRESH_TOKEN));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        authService.logout(request);
        ResponseCookie refreshToken = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("None")
                .build();
        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, refreshToken.toString())
                .build();
    }
}
