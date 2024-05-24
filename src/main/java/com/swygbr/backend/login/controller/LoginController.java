package com.swygbr.backend.login.controller;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.login.dto.TokenResponseDto;
import com.swygbr.backend.login.dto.UserDto;
import com.swygbr.backend.login.property.KakaoProperty;
import com.swygbr.backend.login.service.LoginService;
import com.swygbr.backend.login.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    private final KakaoProperty kakaoProperty;
    private final JwtUtil jwtUtil;

    @GetMapping("/page/kakao")
    ResponseEntity<?> getLoginPageKakao() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        String loginPage = String.format(
                "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s",
                kakaoProperty.getKakaoClientId(), kakaoProperty.getKakaoRedirectionUri());
        headers.setLocation(URI.create(loginPage));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/redirect/kakao")
    ResponseEntity<?> kakaoCallback(@RequestParam(required = true) String code,
            @RequestParam(required = false) String error) {
        if (error != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인 에러");
        }

        UserDto userDto = loginService.kakaoCallback(code);
        String refreshToken = jwtUtil.createRefreshToken(userDto.getId(), userDto.getEmail());
        String accessToken = jwtUtil.createAccessToken(refreshToken);
        TokenResponseDto tokenResponseDto = new TokenResponseDto(accessToken, refreshToken);
        return ResponseEntity.ok(tokenResponseDto);
    }
}