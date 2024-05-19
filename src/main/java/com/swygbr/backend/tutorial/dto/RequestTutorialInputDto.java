package com.swygbr.backend.tutorial.dto;

import com.swygbr.backend.tutorial.enums.TutorialMessageInputType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestTutorialInputDto(@NotNull TutorialMessageInputType inputType,
        @Size(max = 8) @Pattern(regexp = "^[^\\s]+$", message = "Username must not contain spaces") String data) {
    // user name는 최대 8글자이고 중간에 빈칸이 있어서는 안된다.
}
