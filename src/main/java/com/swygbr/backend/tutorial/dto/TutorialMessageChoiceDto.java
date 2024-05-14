package com.swygbr.backend.tutorial.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swygbr.backend.tutorial.controller.TutorialController;
import com.swygbr.backend.tutorial.domain.TutorialMessageChoiceEntity;
import com.swygbr.backend.tutorial.enums.TutorialMessageChoiceType;

@JsonIgnoreProperties({ "reactionTutorialFk" })
public record TutorialMessageChoiceDto(TutorialMessageChoiceType choiceType, String content, String reaction) {
  public static TutorialMessageChoiceDto fromEntity(TutorialMessageChoiceEntity entity) {
    if (Objects.isNull(entity)) {
      return null;
    }

    String reaction = null;
    if (entity.getReaction() != null) {
      reaction = linkTo(TutorialController.class).slash(entity.getReaction().getId()).toString();
    }

    return new TutorialMessageChoiceDto(entity.getChoiceType(), entity.getContent(), reaction);
  }
}
