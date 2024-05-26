package com.swygbr.backend.user.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ImgurProperty {
    @Value("${smalltalker.oauth.imgur.upload-url}")
    private String imgurUploadUrl;

    @Value("${smalltalker.oauth.imgur.client-id}")
    private String imgurClientId;

}