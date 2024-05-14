package com.swygbr.backend.tutorial.dto;

import com.swygbr.backend.tutorial.enums.TutorialMessageInputType;

public record RequestTutorialInputDto(TutorialMessageInputType inputType, String data) {

}
