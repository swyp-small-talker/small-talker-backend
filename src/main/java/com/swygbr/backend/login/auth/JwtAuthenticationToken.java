package com.swygbr.backend.login.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final Long userId; // 주체, 일반적으로 사용자 ID 또는 사용자명
    private final String email;
    private final String token; // JWT 토큰 자체

    public JwtAuthenticationToken(Long userId, String email, String token) {
        super(null);
        this.userId = userId;
        this.email = email;
        this.token = token;
        setAuthenticated(true); // 초기 인증 상태는 false
    }

    public String getEmail(){
        return email;
    }

    @Override
    public Object getPrincipal() {
        return userId; // 주체를 반환
    }

    @Override
    public Object getCredentials() {
        return token; // 인증에 사용되는 자격 증명은 JWT 토큰 자체입니다.
    }

}
