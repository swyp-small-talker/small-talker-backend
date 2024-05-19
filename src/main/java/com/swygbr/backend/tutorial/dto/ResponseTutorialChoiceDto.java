package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.swygbr.backend.tutorial.controller.UserCardController;
import com.swygbr.backend.tutorial.domain.UserCardEntity;

public record ResponseTutorialChoiceDto(String userCardLink) {

    public static ResponseTutorialChoiceDto fromEntity(UserCardEntity entity) {
        String userCardLink = null;
        if (entity != null) {
            userCardLink = linkTo(UserCardController.class).slash(entity.getId()).toString();
        }

        return new ResponseTutorialChoiceDto(userCardLink);
    }

}
