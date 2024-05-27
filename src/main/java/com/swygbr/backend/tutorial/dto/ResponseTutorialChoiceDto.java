package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.swygbr.backend.tutorial.controller.UserCardController;
import com.swygbr.backend.tutorial.domain.UserCardEntity;

public record ResponseTutorialChoiceDto(boolean status) {

    public static EntityModel<ResponseTutorialChoiceDto> fromEntity(UserCardEntity entity, Boolean status) {
        ResponseTutorialChoiceDto responseTutorialChoiceDto = new ResponseTutorialChoiceDto(status);
        EntityModel<ResponseTutorialChoiceDto> model = EntityModel.of(responseTutorialChoiceDto);
        Link userCardLink = linkTo(methodOn(UserCardController.class).getUserCard(entity.getId()))
                .withRel("userCard");
        model.add(userCardLink);
        return model;
    }

}
