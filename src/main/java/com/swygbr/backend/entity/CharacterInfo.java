package com.swygbr.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// TB_CHARACTER_MAIN
@Entity
@Table(name = "TB_CHARACTER_INFO")
@Getter
@Setter
public class CharacterInfo {
    @Id
    @Column(name = "info_id", length = 255)
    private String infoId;

    @Column(name = "character_id", length = 255)
    private String characterId;

    @Column(name = "info_category_nm", length = 255)
    private String infoCategoryNm;

    // 기본 생성자
    public CharacterInfo() {}

    // 모든 필드를 포함하는 생성자

    public CharacterInfo(String infoId, String characterId, String infoCategoryNm) {
        this.infoId = infoId;
        this.characterId = characterId;
        this.infoCategoryNm = infoCategoryNm;
    }

}
