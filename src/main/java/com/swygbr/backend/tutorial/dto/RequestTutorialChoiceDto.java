package com.swygbr.backend.tutorial.dto;

import java.util.List;

import com.swygbr.backend.tutorial.enums.TutorialType;

public record RequestTutorialChoiceDto(TutorialType tutorialType, List<TutorialUserChoiceDto> userChoices) {
}
