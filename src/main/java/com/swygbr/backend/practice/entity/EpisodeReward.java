package com.swygbr.backend.practice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_EPISODE_REWARD")
@Getter
@Setter
@IdClass(EpisodeRewardPk.class)
public class EpisodeReward {
    @Id
    @Column(name = "reward_id", length = 255)
    private String rewardId;

    @Id
    @Column(name = "episode_id", length = 255)
    private String episodeId;

    @Id
    @Column(name = "character_id", length = 255)
    private String characterId;

    @Column(name = "reward_nm")
    private String rewardNm;

    public EpisodeReward() {
    }

    public EpisodeReward(String rewardId, String episodeId, String characterId, String rewardNm) {
        this.rewardId = rewardId;
        this.episodeId = episodeId;
        this.characterId = characterId;
        this.rewardNm = rewardNm;
    }
}
