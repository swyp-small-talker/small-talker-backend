package com.swygbr.backend.practice.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.swygbr.backend.practice.controller.PracticeController;
import com.swygbr.backend.practice.domain.PracticeKeyword;

public record CharacterKeywordResponseDto(int totalKeywordCount, int acquireKeywordCount,
        List<KeywordResponseDto> accquireKeywordList) {

    public static EntityModel<CharacterKeywordResponseDto> fromEntities(String characterId, int totalKeywordCount,
            int acquireKeywordCount,
            List<PracticeKeyword> keywordEntities) {
        List<KeywordResponseDto> dtoList = new ArrayList<>();
        for (PracticeKeyword entity : keywordEntities) {
            KeywordResponseDto dto = new KeywordResponseDto(entity.getCategory(), entity.getContent());
            dtoList.add(dto);
        }

        CharacterKeywordResponseDto acquireKeywordResponseDto = new CharacterKeywordResponseDto(totalKeywordCount,
                acquireKeywordCount, dtoList);
        EntityModel<CharacterKeywordResponseDto> model = EntityModel.of(acquireKeywordResponseDto);
        Link episodeLink = linkTo(methodOn(PracticeController.class).getCharacter(characterId, null))
                .withRel("character");
        model.add(episodeLink);
        return model;
    }

    record KeywordResponseDto(String category, String content) {

    }

}
