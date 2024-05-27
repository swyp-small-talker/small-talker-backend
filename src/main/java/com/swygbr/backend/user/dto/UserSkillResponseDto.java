package com.swygbr.backend.user.dto;

import com.swygbr.backend.practice.domain.PracticeSkill;

public record UserSkillResponseDto(String title, String content) {
    public static UserSkillResponseDto fromEntity(PracticeSkill practiceSkill) {
        return new UserSkillResponseDto(practiceSkill.getTitle(), practiceSkill.getContent());
    }
}
