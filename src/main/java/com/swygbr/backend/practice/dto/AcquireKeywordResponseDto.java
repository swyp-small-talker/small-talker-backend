package com.swygbr.backend.practice.dto;

import java.util.List;

import org.springframework.hateoas.EntityModel;

import com.swygbr.backend.practice.domain.PracticeKeyword;

public record AcquireKeywordResponseDto() {

    public static EntityModel<AcquireKeywordResponseDto> fromEntities(List<PracticeKeyword> keywordEntities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromEntities'");
    }

}
