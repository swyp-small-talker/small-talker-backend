package com.swygbr.backend.tutorial.dto;

import java.util.List;

import com.swygbr.backend.tutorial.domain.TutorialEntity;
import com.swygbr.backend.tutorial.enums.TutorialType;

public record TutorialDto(Long id, TutorialType tutorialType, Boolean isStart,
        List<TutorialMessageDto> tutorialMessages) {

    public static TutorialDto fromEntity(TutorialEntity entity) {
        return new TutorialDto(entity.getId(), entity.getTutorialType(), entity.getIsStart(),
                entity.getTutorialMessages().stream().map(TutorialMessageDto::fromEntity).toList());
    }
}
