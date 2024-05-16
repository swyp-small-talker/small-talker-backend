package com.swygbr.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// TB_CHARACTER_MAIN
@Entity
@Table(name = "TB_CHARACTER_MAIN")
@Getter
@Setter
public class CharacterMain {
    @Id
    @Column(name = "character_id", length = 255)
    private String characterId;

    @Column(name = "character_nm", length = 255)
    private String characterNm;

    @Column(name = "character_type", length = 255)
    private String characterType;

    // 기본 생성자
    public CharacterMain() {}

    // 모든 필드를 포함하는 생성자
    public CharacterMain(String characterId, String characterNm, String characterType) {
        this.characterId = characterId;
        this.characterNm = characterNm;
        this.characterType = characterType;
    }

    // Getter/Setter 메소드
    // ...
}
