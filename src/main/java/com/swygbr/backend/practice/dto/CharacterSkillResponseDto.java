package com.swygbr.backend.practice.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.swygbr.backend.practice.controller.PracticeController;
import com.swygbr.backend.practice.domain.PracticeSkill;

public record CharacterSkillResponseDto(int totalSkillCount, int acquireSkillCount,
        List<SkillResponseDto> acquireSkillList) {

    public static EntityModel<CharacterSkillResponseDto> fromEntities(String characterId, int totalSkillCount,
            int acquireSkillCount, List<PracticeSkill> acquireSkillList) {
        List<SkillResponseDto> dtoList = new ArrayList<>();

        for (PracticeSkill entity : acquireSkillList) {
            SkillResponseDto dto = new SkillResponseDto(entity.getTitle(), entity.getContent());
            dtoList.add(dto);
        }

        CharacterSkillResponseDto acquireSkillResponseDto = new CharacterSkillResponseDto(totalSkillCount,
                acquireSkillCount, dtoList);
        EntityModel<CharacterSkillResponseDto> model = EntityModel.of(acquireSkillResponseDto);
        Link characterLink = linkTo(methodOn(PracticeController.class).getCharacter(characterId, null))
                .withRel("character");
        model.add(characterLink);
        return model;
    }

    record SkillResponseDto(String title, String content) {

    }
}
