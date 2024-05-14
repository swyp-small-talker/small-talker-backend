package com.swygbr.backend.tutorial.dto;

import com.swygbr.backend.tutorial.domain.UserCardEntity;

public record UserCardDto(Long id, String picture, String title, String content) {
    public static UserCardDto fromEntity(UserCardEntity entity) {
        return new UserCardDto(entity.getId(), entity.getPicture(), entity.getTitle(), entity.getContent());
    }

}
