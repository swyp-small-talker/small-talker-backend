package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.swygbr.backend.tutorial.controller.TutorialController;
import com.swygbr.backend.tutorial.domain.TutorialEntity;
import com.swygbr.backend.tutorial.enums.TutorialType;

public record TutorialDto(Long id, TutorialType tutorialType, Boolean isStart,
        CollectionModel<EntityModel<TutorialMessageDto>> tutorialMessageList) {

    public static EntityModel<TutorialDto> fromEntity(TutorialEntity entity) {
        List<EntityModel<TutorialMessageDto>> messageCollectionList = entity.getTutorialMessages().stream()
                .map(TutorialMessageDto::fromEntity).toList();
        CollectionModel<EntityModel<TutorialMessageDto>> tutorialMessageList = CollectionModel
                .of(messageCollectionList);

        TutorialDto dto = new TutorialDto(entity.getId(), entity.getTutorialType(), entity.getIsStart(),
                tutorialMessageList);

        EntityModel<TutorialDto> model = EntityModel.of(dto);
        if (entity.getNextTutorial() != null) {
            Link nextLink = linkTo(methodOn(TutorialController.class).getTutorial(entity.getNextTutorial().getId()))
                    .withRel("next");
            model.add(nextLink);
        }

        return model;
    }
}
