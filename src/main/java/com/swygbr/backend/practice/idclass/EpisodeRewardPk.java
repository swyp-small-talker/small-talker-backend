package com.swygbr.backend.practice.idclass;

import java.io.Serializable;
import java.util.Objects;

public class EpisodeRewardPk implements Serializable {
    private String rewardId;
    private String episodeId;

    private String characterId;

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
