package com.hoang.AuthenticationSystem.validation.constraints;

import com.hoang.AuthenticationSystem.validation.validator.UserNameOrEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UserNameOrEmailValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameOrEmail {
    String message() default "Username or email address is invalid";

    int usernameMin() default 3;

    int usernameMax() default 30;

    int emailMax() default 254;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
