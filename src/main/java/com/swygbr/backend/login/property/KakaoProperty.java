package com.swygbr.backend.login.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class KakaoProperty {
    @Value("${smalltalker.oauth.kakao.client-id}")
    private String kakaoClientId;

    @Value("${smalltalker.oauth.kakao.redirect-uri}")
    private String kakaoRedirectionUri;

}