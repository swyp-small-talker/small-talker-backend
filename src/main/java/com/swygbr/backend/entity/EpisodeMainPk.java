package com.swygbr.backend.entity;

import java.io.Serializable;
import java.util.Objects;

// EpisodeMainPk (복합 기본키 클래스)
public class EpisodeMainPk implements Serializable {
    private String episodeId;
    private String characterId;

    // 기본 생성자
    public EpisodeMainPk() {}

    // 모든 필드를 포함하는 생성자
    public EpisodeMainPk(String episodeId, String characterId) {
        this.episodeId = episodeId;
        this.characterId = characterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpisodeMainPk that = (EpisodeMainPk) o;
        return Objects.equals(episodeId, that.episodeId) &&
                Objects.equals(characterId, that.characterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodeId, characterId);
    }
}
