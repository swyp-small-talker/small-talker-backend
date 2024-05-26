package com.swygbr.backend.practice.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.swygbr.backend.practice.controller.PracticeController;
import com.swygbr.backend.practice.domain.PracticeEpisodeComplete;

public record EpisodeCompleteResponseDto(Boolean status) {

    public static EntityModel<EpisodeCompleteResponseDto> fromEntity(PracticeEpisodeComplete entity) {
        EpisodeCompleteResponseDto dto = new EpisodeCompleteResponseDto(true);
        EntityModel<EpisodeCompleteResponseDto> model = EntityModel.of(dto);

        String characterId = entity.getEpisode().getCharacter().getId();

        Link episodeLink = linkTo(
                methodOn(PracticeController.class).getCharacterEpisode(characterId, null))
                .withRel("episode");
        model.add(episodeLink);
        Link characterLink = linkTo(methodOn(PracticeController.class).getCharacter(characterId, null))
                .withRel("character");
        model.add(characterLink);

        return model;
    }
}