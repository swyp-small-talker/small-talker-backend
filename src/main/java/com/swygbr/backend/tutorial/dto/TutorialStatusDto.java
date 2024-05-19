package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.swygbr.backend.tutorial.controller.TutorialController;
import com.swygbr.backend.tutorial.domain.TutorialEntity;
import com.swygbr.backend.tutorial.enums.TutorialType;
import com.swygbr.backend.user.domain.UserEntity;

public record TutorialStatusDto(TutorialType tutorialType, boolean isCompleted, String link) {

    public static TutorialStatusDto fromEntity(UserEntity userEntity, TutorialEntity tutorialEntity) {
        TutorialType tutorialType = tutorialEntity.getTutorialType();
        boolean isComplete = isTutorialComplete(userEntity, tutorialType);
        String link = linkTo(TutorialController.class).slash(tutorialEntity.getId()).toString();

        return new TutorialStatusDto(tutorialType, isComplete, link);
    }

    private static boolean isTutorialComplete(UserEntity userEntity, TutorialType tutorialType) {
        if (tutorialType == TutorialType.PROFILE) {
            return userEntity.getCompleteProfileTypeTutorial();
        }
        return userEntity.getCompleteUserCardTypeTutorial();
    }

}
