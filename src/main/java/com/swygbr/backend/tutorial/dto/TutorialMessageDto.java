package com.swygbr.backend.tutorial.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.swygbr.backend.tutorial.domain.TutorialMessageEntity;
import com.swygbr.backend.tutorial.enums.TutorialMessageType;

@JsonInclude(Include.NON_EMPTY)
public record TutorialMessageDto(Long id, TutorialMessageType messageType, TutorialMessageTextDto text,
        List<TutorialMessageChoiceDto> choices, TutorialMessageInputDto input) {
    static public TutorialMessageDto fromEntity(TutorialMessageEntity entity) {
        return new TutorialMessageDto(entity.getId(), entity.getMessageType(),
                TutorialMessageTextDto.fromEntity(entity.getText()),
                entity.getChoices().stream().map(TutorialMessageChoiceDto::fromEntity).toList(),
                TutorialMessageInputDto.fromEntity(entity.getInput()));
    }
}