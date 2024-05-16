package com.swygbr.backend.entity;

import java.io.Serializable;
import java.util.Objects;

// EpisodeMainPk (복합 기본키 클래스)
public class EpisodeRewardPk implements Serializable {
    private String rewardId;
    private String episodeId;

    private String characterId;

    // 기본 생성자
    public EpisodeRewardPk() {
    }

    public EpisodeRewardPk(String rewardId, String episodeId, String characterId) {
        this.rewardId = rewardId;
        this.episodeId = episodeId;
        this.characterId = characterId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpisodeRewardPk that = (EpisodeRewardPk) o;
        return Objects.equals(episodeId, that.episodeId) &&
                Objects.equals(characterId, that.characterId) &&
                Objects.equals(rewardId, that.rewardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rewardId, episodeId, characterId);
    }
}
