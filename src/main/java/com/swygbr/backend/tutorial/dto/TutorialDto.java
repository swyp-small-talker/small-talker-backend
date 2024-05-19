package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import com.swygbr.backend.tutorial.controller.TutorialController;
import com.swygbr.backend.tutorial.domain.TutorialEntity;
import com.swygbr.backend.tutorial.enums.TutorialType;

public record TutorialDto(Long id, TutorialType tutorialType, Boolean isStart,
        List<TutorialMessageDto> tutorialMessages, String next) {

    public static TutorialDto fromEntity(TutorialEntity entity) {
        String nextLink = null;
        if (entity.getNextTutorial() != null) {
            nextLink = linkTo(TutorialController.class).slash(entity.getNextTutorial().getId()).toString();
        }

        return new TutorialDto(entity.getId(),
                entity.getTutorialType(),
                entity.getIsStart(),
                entity.getTutorialMessages().stream().map(TutorialMessageDto::fromEntity).toList(),
                nextLink);
    }
}
