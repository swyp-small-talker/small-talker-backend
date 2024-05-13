package com.swygbr.backend.tutorial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swygbr.backend.tutorial.domain.TutorialMessageChoiceEntity;
import com.swygbr.backend.tutorial.enums.TutorialMessageChoiceType;

@JsonIgnoreProperties({ "reactionTutorialFk" })
public record TutorialMessageChoiceDto(Long reactionTutorialId, TutorialMessageChoiceType choiceType, String content) {
  public static TutorialMessageChoiceDto fromEntity(TutorialMessageChoiceEntity entity) {
    return new TutorialMessageChoiceDto(entity.getReaction().getId(), entity.getChoiceType(), entity.getContent());
  }
}
