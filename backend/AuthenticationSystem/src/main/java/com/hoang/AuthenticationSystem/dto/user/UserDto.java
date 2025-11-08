package com.hoang.AuthenticationSystem.dto.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    UUID id;
    String username;
    String email;
    String avatarUrl;
    String firstname;
    String lastname;
}
