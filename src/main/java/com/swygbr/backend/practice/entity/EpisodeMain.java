package com.swygbr.backend.practice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// TB_EPISODE_MAIN
@Entity
@Getter
@Setter
@Table(name = "TB_EPISODE_MAIN")
@IdClass(EpisodeMainPk.class)
public class EpisodeMain {
    @Id
    @Column(name = "episode_id", length = 255)
    private String episodeId;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "character_id", length = 255)
    private String characterId;

    @Column(name = "episode_title", length = 255)
    private String episodeTitle;

    @Column(name = "episode_complete_yn", length = 255)
    private String episodeCompleteYn;

    public EpisodeMain() {
    }

    public EpisodeMain(String episodeId, String characterId, String userId, String episodeTitle, String episodeCompleteYn) {
        this.episodeId = episodeId;
        this.characterId = characterId;
        this.userId = userId;
        this.episodeTitle = episodeTitle;
        this.episodeCompleteYn = episodeCompleteYn;
    }

}
