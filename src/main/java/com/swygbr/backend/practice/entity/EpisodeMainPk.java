package com.swygbr.backend.practice.entity;

import java.io.Serializable;
import java.util.Objects;

public class EpisodeMainPk implements Serializable {
    private String episodeId;
    private String characterId;

    private String userId;

    public EpisodeMainPk() {}

    public EpisodeMainPk(String episodeId, String characterId, String user_id) {
        this.episodeId = episodeId;
        this.characterId = characterId;
        this.userId = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpisodeMainPk that = (EpisodeMainPk) o;
        return Objects.equals(episodeId, that.episodeId) &&
                Objects.equals(characterId, that.characterId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodeId, characterId, userId);
    }
}
