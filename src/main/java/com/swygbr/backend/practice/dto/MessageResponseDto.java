package com.swygbr.backend.practice.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.swygbr.backend.practice.controller.PracticeController;
import com.swygbr.backend.practice.domain.PracticeMessage;

public record MessageResponseDto(String id, String actor, String messageType, String content,
        CollectionModel<EntityModel<ChoiceMessageResponseDto>> choiceList) {

    public static EntityModel<MessageResponseDto> fromEntity(PracticeMessage entity) {
        CollectionModel<EntityModel<ChoiceMessageResponseDto>> choiceList = CollectionModel.empty();

        if (entity.getMessageType().equals("CHOICE_PARENT")) {
            List<PracticeMessage> childEntityList = entity.getChildList();
            List<EntityModel<ChoiceMessageResponseDto>> choiceCollectionList = new ArrayList<>();
            for (PracticeMessage childEntity : childEntityList) {
                ChoiceMessageResponseDto dto = new ChoiceMessageResponseDto(childEntity.getId(),
                        childEntity.getActor(),
                        childEntity.getMessageType(), childEntity.getContent());

                EntityModel<ChoiceMessageResponseDto> model = EntityModel.of(dto);
                String nextId = childEntity.getChildList().get(0).getId();
                Link nextLink = linkTo(methodOn(PracticeController.class).getMessage(nextId))
                        .withRel("next");
                model.add(nextLink);
                choiceCollectionList.add(model);
            }

            choiceList = CollectionModel.of(choiceCollectionList);

            MessageResponseDto dto = new MessageResponseDto(entity.getId(), entity.getActor(),
                    entity.getMessageType(),
                    entity.getContent(), choiceList);
            EntityModel<MessageResponseDto> model = EntityModel.of(dto);

            return model;
        } else {
            MessageResponseDto dto = new MessageResponseDto(entity.getId(), entity.getActor(),
                    entity.getMessageType(),
                    entity.getContent(), choiceList);
            EntityModel<MessageResponseDto> model = EntityModel.of(dto);

            if (entity.getChildList().size() == 1) {
                String nextId = entity.getChildList().get(0).getId();
                Link nextLink = linkTo(methodOn(PracticeController.class).getMessage(nextId))
                        .withRel("next");
                model.add(nextLink);
            }
            return model;
        }
    }
}
