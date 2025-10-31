package com.hoang.AuthenticationSystem.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReSendVerificationCodeRequest {
    @NotBlank(message = "{NOT_BLANK}")
    @Email(message = "{EMAIL_REGEX}")
    @Size(max = 254, message = "{EMAIL_SIZE}")
    private String email;
}
