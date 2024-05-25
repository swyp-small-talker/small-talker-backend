package com.swygbr.backend.practice.entity;

import java.io.Serializable;
import java.util.Objects;

public class EpisodeMainPk implements Serializable {
    private String episodeId;

    private Long userId;

    public EpisodeMainPk() {
    }

    public EpisodeMainPk(String episodeId, Long userId) {
        this.episodeId = episodeId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EpisodeMainPk that = (EpisodeMainPk) o;
        return Objects.equals(episodeId, that.episodeId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodeId, userId);
    }
}
