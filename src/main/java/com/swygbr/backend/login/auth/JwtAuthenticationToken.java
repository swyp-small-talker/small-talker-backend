package com.swygbr.backend.login.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final JwtUserPrincipal principal;
    private final String token; // JWT 토큰 자체

    public JwtAuthenticationToken(JwtUserPrincipal principal, String token) {
        super(null);
        this.principal = principal;
        this.token = token;
        setAuthenticated(true); // 초기 인증 상태는 false
    }

    @Override
    public Object getPrincipal() {
        return principal; // 주체를 반환
    }

    @Override
    public Object getCredentials() {
        return token; // 인증에 사용되는 자격 증명은 JWT 토큰 자체입니다.
    }

}
