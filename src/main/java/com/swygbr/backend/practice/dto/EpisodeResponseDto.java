package com.swygbr.backend.practice.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.swygbr.backend.practice.controller.PracticeController;
import com.swygbr.backend.practice.domain.PracticeEpisode;

public record EpisodeResponseDto(String id, String title, boolean complete) {

    public static EntityModel<EpisodeResponseDto> fromEntity(PracticeEpisode entity, boolean complete,
            String startMessageId) {
        EpisodeResponseDto dto = new EpisodeResponseDto(entity.getId(), entity.getTitle(), complete);

        EntityModel<EpisodeResponseDto> model = EntityModel.of(dto);

        Link start = linkTo(methodOn(PracticeController.class).getMessage(startMessageId))
                .withRel("start");
        model.add(start);

        return model;
    }

}
