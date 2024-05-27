package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.swygbr.backend.tutorial.controller.TutorialController;
import com.swygbr.backend.tutorial.domain.TutorialEntity;
import com.swygbr.backend.tutorial.enums.TutorialType;
import com.swygbr.backend.user.domain.UserEntity;

public record TutorialStatusDto(TutorialType tutorialType, boolean isCompleted) {

    public static EntityModel<TutorialStatusDto> fromEntity(UserEntity userEntity,
            TutorialEntity tutorialEntity) {
        TutorialType tutorialType = tutorialEntity.getTutorialType();
        boolean isComplete = isTutorialComplete(userEntity, tutorialType);
        TutorialStatusDto dto = new TutorialStatusDto(tutorialType, isComplete);

        EntityModel<TutorialStatusDto> model = EntityModel.of(dto);
        Link startLink = linkTo(methodOn(TutorialController.class).getTutorial(tutorialEntity.getId()))
                .withRel("start");
        model.add(startLink);

        return model;
    }

    private static boolean isTutorialComplete(UserEntity userEntity, TutorialType tutorialType) {
        if (tutorialType == TutorialType.PROFILE) {
            return userEntity.getCompleteProfileTypeTutorial();
        }
        return userEntity.getCompleteUserCardTypeTutorial();
    }

}
