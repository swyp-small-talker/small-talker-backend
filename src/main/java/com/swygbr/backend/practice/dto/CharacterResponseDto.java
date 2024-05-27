package com.swygbr.backend.practice.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.swygbr.backend.practice.controller.PracticeController;
import com.swygbr.backend.practice.domain.PracticeCharacter;

public record CharacterResponseDto(String characterId, String name, String type, boolean complete) {

        public static EntityModel<CharacterResponseDto> fromEntity(PracticeCharacter entity, boolean complete) {
                CharacterResponseDto dto = new CharacterResponseDto(entity.getId(), entity.getName(),
                                entity.getCharacterType(),
                                complete);
                EntityModel<CharacterResponseDto> model = EntityModel.of(dto);

                Link episodeLink = linkTo(
                                methodOn(PracticeController.class).getCharacterEpisode(dto.characterId(), null))
                                .withRel("episode");
                model.add(episodeLink);
                Link keywordLink = linkTo(
                                methodOn(PracticeController.class).getCharacterKeywords(dto.characterId, null))
                                .withRel("keyword");
                model.add(keywordLink);
                Link skillLink = linkTo(
                                methodOn(PracticeController.class).getCharacterSkill(dto.characterId, null))
                                .withRel("skill");
                model.add(skillLink);

                return model;
        }
}
