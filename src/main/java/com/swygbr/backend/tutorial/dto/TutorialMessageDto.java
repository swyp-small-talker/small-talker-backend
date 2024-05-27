package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.swygbr.backend.tutorial.domain.TutorialMessageEntity;
import com.swygbr.backend.tutorial.enums.TutorialMessageType;
import com.swygbr.backend.user.controller.UserController;

@JsonInclude(Include.NON_EMPTY)
public record TutorialMessageDto(Long id, TutorialMessageType messageType, TutorialMessageTextDto text,
        CollectionModel<EntityModel<TutorialMessageChoiceDto>> choiceList) {
    static public EntityModel<TutorialMessageDto> fromEntity(TutorialMessageEntity entity) {
        TutorialMessageTextDto text = TutorialMessageTextDto.fromEntity(entity.getText());

        List<EntityModel<TutorialMessageChoiceDto>> choiceCollectionList = entity.getChoices().stream()
                .map(TutorialMessageChoiceDto::fromEntity).toList();
        CollectionModel<EntityModel<TutorialMessageChoiceDto>> choiceList = CollectionModel
                .of(choiceCollectionList);

        TutorialMessageDto dto = new TutorialMessageDto(entity.getId(), entity.getMessageType(), text,
                choiceList);
        EntityModel<TutorialMessageDto> model = EntityModel.of(dto);

        if (dto.messageType == TutorialMessageType.INPUT_NAME) {
            Link nextLink = linkTo(methodOn(UserController.class).putUserById(null, null))
                    .withRel("next");
            model.add(nextLink);
        }
        return model;
    }
}