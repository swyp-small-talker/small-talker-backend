package com.swygbr.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// TB_CHARACTER_MAIN
@Entity
@Table(name = "TB_CHARACTER_INFO_DETAIL")
@Getter
@Setter
@IdClass(CharacterInfoDetailPk.class)
public class CharacterInfoDetail {
    @Id
    @Column(name = "info_detail_id", length = 255)
    private String infoDetailId;

    @Id
    @Column(name = "info_id", length = 255)
    private String infoId;

    @Column(name = "info_detail_nm", length = 255)
    private String infoDetailNm;

    // 기본 생성자
    public CharacterInfoDetail() {
    }

    // 모든 필드를 포함하는 생성자

    public CharacterInfoDetail(String infoDetailId, String infoId, String infoDetailNm) {
        this.infoDetailId = infoDetailId;
        this.infoId = infoId;
        this.infoDetailNm = infoDetailNm;
    }

    // Getter/Setter 메소드
    // ...
}
