package com.swygbr.backend.mypage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MypageDTO {
    private Long id;
    private String name;
    private Double completionRate;

    public MypageDTO(Long id, String name, Double completionRate) {
        this.id = id;
        this.name = name;
        this.completionRate = completionRate;
    }
}
