package com.swygbr.backend.practice.entity;

import com.swygbr.backend.practice.idclass.CharacterInfoDetailPk;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    public CharacterInfoDetail() {
    }


    public CharacterInfoDetail(String infoDetailId, String infoId, String infoDetailNm) {
        this.infoDetailId = infoDetailId;
        this.infoId = infoId;
        this.infoDetailNm = infoDetailNm;
    }

}
