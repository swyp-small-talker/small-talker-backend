package com.swygbr.backend.practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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

    public CharacterInfo() {}

    public CharacterInfo(String infoId, String characterId, String infoCategoryNm) {
        this.infoId = infoId;
        this.characterId = characterId;
        this.infoCategoryNm = infoCategoryNm;
    }

}
