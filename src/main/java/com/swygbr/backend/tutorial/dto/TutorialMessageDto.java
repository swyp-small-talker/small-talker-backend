package com.swygbr.backend.tutorial.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.swygbr.backend.tutorial.domain.TutorialMessageEntity;
import com.swygbr.backend.tutorial.enums.TutorialMessageType;

@JsonInclude(Include.NON_NULL)
public record TutorialMessageDto(Long id, TutorialMessageType messageType, TutorialMessageTextDto text,
        List<TutorialMessageChoiceDto> choices) {
    static public TutorialMessageDto fromEntity(TutorialMessageEntity entity) {
        return new TutorialMessageDto(entity.getId(), entity.getMessageType(),
                TutorialMessageTextDto.fromEntity(entity.getText()),
                entity.getChoices().stream().map(TutorialMessageChoiceDto::fromEntity).toList());
    }
    // @Builder
    // public TutorialMessageDto(Long id, Tutorial botType, TutorialType
    // tutorialType,
    // TutorialMessageDto message,
    // TutorialLastMessageDto lastMessage, TutorialQuestionDto question,
    // TutorialChoiceDto choice,
    // TutorialInsertDto insert) {
    // this.id = id;
    // this.botType = botType;
    // this.tutorialType = tutorialType;
    // this.message = message;
    // this.lastMessage = lastMessage;
    // this.question = question;
    // this.choice = choice;
    // this.insert = insert;
    // }

    // public void setChildId(Long childId) {
    // this.childId = childId;
    // }

    // public static TutorialMessageDto fromEntity(TutorialEntity entity) {
    // TutorialMessageDtoBuilder builder = TutorialMessageDto.builder();
    // builder.id(entity.getId())
    // .botType(entity.getBotType())
    // .tutorialType(entity.getTutorialType());
    // if (TutorialType.MESSAGE == entity.getTutorialType()) {
    // builder.message(TutorialMessageDto.fromEntity(entity));
    // } else if (TutorialType.LAST_MESSAGE == entity.getTutorialType()) {
    // builder.lastMessage(TutorialLastMessageDto.fromEntity(entity));
    // } else if (TutorialType.QUESTION == entity.getTutorialType()) {
    // builder.question(TutorialQuestionDto.fromEntity(entity));
    // } else if (TutorialType.CHOICE == entity.getTutorialType()) {
    // builder.choice(TutorialChoiceDto.fromEntity(entity));
    // } else if (TutorialType.INSERT == entity.getTutorialType()) {
    // builder.insert(TutorialInsertDto.fromEntity(entity));
    // }

    // return builder.build();
    // }

}