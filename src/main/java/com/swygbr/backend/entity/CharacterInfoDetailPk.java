package com.swygbr.backend.entity;

import java.io.Serializable;
import java.util.Objects;

public class CharacterInfoDetailPk implements Serializable {
    private String infoDetailId;
    private String infoId;

    // 기본 생성자
    public CharacterInfoDetailPk() {}

    // 모든 필드를 포함하는 생성자
    public CharacterInfoDetailPk(String infoDetailId, String infoId) {
        this.infoDetailId = infoDetailId;
        this.infoId = infoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterInfoDetailPk that = (CharacterInfoDetailPk) o;
        return Objects.equals(infoDetailId, that.infoDetailId) &&
                Objects.equals(infoId, that.infoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoDetailId, infoId);
    }

}
