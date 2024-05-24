package com.swygbr.backend.login.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import com.swygbr.backend.login.property.JwtProperty;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
    private final JwtProperty jwtProperty;
    private final SecretKey key;

    public JwtUtil(JwtProperty jwtProperty) {
        this.jwtProperty = jwtProperty;
        this.key = Jwts.SIG.HS256.key().build();
    }

    private String createToken(TokenType tokenType, long accessTokenExpirationInSeconds, String id, String issuser) {
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(
                tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenExpirationInSeconds));
        return Jwts.builder()
                .subject(tokenType.name())
                .id(id)
                .issuer(issuser)
                .expiration(tokenValidity)
                .signWith(key)
                .compact();
    }

    public String createAccessToken(String refreshToken) {
        if (this.isRefreshToken(refreshToken)) {
            return createToken(TokenType.ACCESS, this.jwtProperty.getAccessTokenExpirationInSeconds(),
                    this.getUserId(refreshToken).toString(), this.getEmail(refreshToken));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String createRefreshToken(Long userId, String email) {
        return createToken(TokenType.REFRESH, this.jwtProperty.getRefreshTokenExpirationInSeconds(), userId.toString(),
                email);
    }

    private Jws<Claims> parseJwtClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.key)
                .build()
                .parseSignedClaims(token);
    }

    // jwt 비밀키 불일치, 시간 만료 등 체크
    public boolean validateToken(String token) {
        try {
            this.parseJwtClaims(token);
            return true;
        } catch (IllegalArgumentException | JwtException ex) {
            return false;
        }
    }

    public boolean isRefreshToken(String refreshToken) {
        if (this.validateToken(refreshToken) && this.getTokenType(refreshToken).equals(TokenType.REFRESH)) {
            return true;
        }
        return false;

    }

    public TokenType getTokenType(String token) {
        Jws<Claims> claims = this.parseJwtClaims(token);
        return TokenType.valueOf(claims.getPayload().getSubject());
    }

    public Long getUserId(String token) {
        Jws<Claims> claims = this.parseJwtClaims(token);
        return Long.valueOf(claims.getPayload().getId());
    }

    public String getEmail(String token) {
        Jws<Claims> claims = this.parseJwtClaims(token);
        return claims.getPayload().getIssuer();
    }

    public Date getExpiration(String token) {
        Jws<Claims> claims = this.parseJwtClaims(token);
        return claims.getPayload().getExpiration();
    }
}