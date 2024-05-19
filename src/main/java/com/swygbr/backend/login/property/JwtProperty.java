package com.swygbr.backend.login.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class JwtProperty {
    @Value("${jwt.token.access.expiration-seconds}")
    long accessTokenExpirationInSeconds;
    @Value("${jwt.token.refresh.expiration-seconds}")
    long refreshTokenExpirationInSeconds;
}