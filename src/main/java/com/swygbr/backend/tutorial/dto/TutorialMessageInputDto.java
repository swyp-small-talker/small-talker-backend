package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Objects;

import com.swygbr.backend.tutorial.controller.TutorialController;
import com.swygbr.backend.tutorial.domain.TutorialMessageInputEntity;
import com.swygbr.backend.tutorial.enums.TutorialMessageInputType;

public record TutorialMessageInputDto(TutorialMessageInputType inputType, String link) {
    public static TutorialMessageInputDto fromEntity(TutorialMessageInputEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        String link = linkTo(TutorialController.class).slash("input").toString();

        return new TutorialMessageInputDto(entity.getInputType(), link);
    }
}
