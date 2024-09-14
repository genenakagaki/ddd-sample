package com.genenakagaki.checklist.security;

import com.genenakagaki.checklist.query.user.UserAuthenticationQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final UserAuthenticationQuery user;

    public CustomUserDetails(UserAuthenticationQuery user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.encodedPassword();
    }

    @Override
    public String getUsername() {
        return user.username().value();
    }
}
