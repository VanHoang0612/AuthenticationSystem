package com.hoang.AuthenticationSystem.security.userDetails;

import com.hoang.AuthenticationSystem.model.User;
import lombok.Getter;

@Getter
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CustomUserDetails(User user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.user = user;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
