package com.swygbr.backend.login.auth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtUserPrincipal {
    private final Long userId;
    private final String email;

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

}
