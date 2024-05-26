package com.swygbr.backend.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImgurResponseDto {
    private ImgurData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ImgurData {
        private String link;
    }
}