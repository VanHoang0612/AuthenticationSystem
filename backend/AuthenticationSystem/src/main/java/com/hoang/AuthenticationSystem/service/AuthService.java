package com.hoang.AuthenticationSystem.service;

import com.hoang.AuthenticationSystem.Exception.AppException;
import com.hoang.AuthenticationSystem.dto.auth.RegisterRequest;
import com.hoang.AuthenticationSystem.enums.ErrorCode;
import com.hoang.AuthenticationSystem.enums.SuccessCode;
import com.hoang.AuthenticationSystem.model.User;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public SuccessCode register(RegisterRequest request) {
        List<String> errors = new ArrayList<>();
        if (userService.existsByUsername(request.getUsername()
                .trim())) {
            errors.add(ErrorCode.USER_EXISTED.getMessage());
        }
        if (userService.existsByEmail(request.getEmail()
                .trim())) {
            errors.add(ErrorCode.EMAIL_EXISTED.getMessage());
        }
        if (!errors.isEmpty()) {
            throw new AppException(ErrorCode.USER_DUPLICATE, errors);
        }
        User user = User.builder()
                .username(request.getUsername()
                        .trim())
                .email(request.getEmail()
                        .trim())
                .password(passwordEncoder.encode(request.getPassword()
                        .trim()))
                .firstname(request.getFirstname()
                        .trim())
                .lastname(request.getLastname()
                        .trim())
                .verificationCode(generateVerificationCode())
                .verificationExpiresAt(LocalDateTime.now()
                        .plusMinutes(10))
                .build();
        try {
            userService.save(user);
            sendVerificationEmail(user);
            return SuccessCode.REGISTER;

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AppException(ErrorCode.INTERNAL_SERVER, List.of(e.getMessage()));
        }


    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    public void sendVerificationEmail(User user) {
        String subject = "Verify your email";
        String verificationCode = user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
            ;
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send verification email", e);
        }
    }
}
