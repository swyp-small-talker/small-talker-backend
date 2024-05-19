package com.swygbr.backend.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swygbr.backend.login.dto.AccessTokenDto;
import com.swygbr.backend.login.dto.RefreshTokenRequestDto;
import com.swygbr.backend.login.dto.TokenValidDto;
import com.swygbr.backend.login.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/token")
@RestController
public class TokenController {
    private final JwtUtil jwtUtil;

    @GetMapping("/valid")
    public ResponseEntity<?> validAccessToken(@RequestBody AccessTokenDto accessTokenDto) {
        if (jwtUtil.validateToken(accessTokenDto.accessToken())) {
            return ResponseEntity.ok(new TokenValidDto(true));
        } else {
            return ResponseEntity.badRequest().body(new TokenValidDto(false));
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshCookie(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        if (jwtUtil.validateToken(refreshTokenRequestDto.refreshToken())) {
            AccessTokenDto accessTokenDto = new AccessTokenDto(
                    jwtUtil.createAccessToken(refreshTokenRequestDto.refreshToken()));
            return ResponseEntity.ok(accessTokenDto);
        } else {
            return ResponseEntity.badRequest().body(new TokenValidDto(false));
        }
    }
}
