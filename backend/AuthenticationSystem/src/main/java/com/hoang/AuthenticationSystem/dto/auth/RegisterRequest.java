package com.hoang.AuthenticationSystem.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "{USERNAME_REGEX}")
    @Size(min = 3, max = 30, message = "{USERNAME_SIZE}")
    String username;

    @Email(message = "{EMAIL_REGEX}")
    @Size(max = 254, message = "{EMAIL_SIZE}")
    String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\\\/-]).*$", message = "{PASSWORD_REGEX}")
    @Size(min = 8, max = 128, message = "{PASSWORD_SIZE}")
    String password;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "{FIRSTNAME_REGEX}")
    @Size(min = 2, max = 50, message = "{FIRSTNAME_SIZE}")
    String firstname;

    @Pattern(regexp = "^[\\p{L} ]+$", message = "{LASTNAME_REGEX}")
    @Size(min = 2, max = 50, message = "{LASTNAME_SIZE}")
    String lastname;
}
