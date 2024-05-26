package com.swygbr.backend.user.service;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.swygbr.backend.user.dto.ImgurResponseDto;
import com.swygbr.backend.user.property.ImgurProperty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImgurService {
    private final ImgurProperty imgurProperty;

    public String uploadImage(String base64Profile) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set(HttpHeaders.AUTHORIZATION, "Client-ID " + imgurProperty.getImgurClientId());

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("type", "base64");
        requestBody.add("image", base64Profile);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ImgurResponseDto> response = restTemplate.exchange(
                imgurProperty.getImgurUploadUrl(),
                HttpMethod.POST,
                requestEntity,
                ImgurResponseDto.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IOException("이미지 업로드 실패");
        }

        ImgurResponseDto imgurResponseDto = response.getBody();
        return imgurResponseDto.getData().getLink();
    }
}