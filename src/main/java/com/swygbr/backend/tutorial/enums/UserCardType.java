package com.swygbr.backend.tutorial.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserCardType {
    REACTION_BOT("리액션봇"),
    TOO_MUCH_TALKER("투머치토커"),
    QUESTION_BOMBER("질문폭격기"),
    MODEL_STUDENT("모범생"),
    SMALL_TALKER("스몰토커");

    private final String title;
}
