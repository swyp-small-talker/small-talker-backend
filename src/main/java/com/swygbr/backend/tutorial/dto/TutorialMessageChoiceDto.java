package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Objects;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swygbr.backend.tutorial.controller.TutorialController;
import com.swygbr.backend.tutorial.domain.TutorialMessageChoiceEntity;
import com.swygbr.backend.tutorial.enums.TutorialMessageChoiceType;

@JsonIgnoreProperties({ "reactionTutorialFk" })
public record TutorialMessageChoiceDto(Long choiceId, TutorialMessageChoiceType choiceType, String content) {
  public static EntityModel<TutorialMessageChoiceDto> fromEntity(TutorialMessageChoiceEntity entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    TutorialMessageChoiceDto tutorialMessageChoiceDto = new TutorialMessageChoiceDto(entity.getId(),
        entity.getChoiceType(), entity.getContent());
    EntityModel<TutorialMessageChoiceDto> model = EntityModel.of(tutorialMessageChoiceDto);

    if (entity.getReaction() != null) {
      Link nextLink = linkTo(methodOn(TutorialController.class).getTutorial(entity.getReaction().getId()))
          .withRel("next");
      model.add(nextLink);
    }

    return model;
  }
}
