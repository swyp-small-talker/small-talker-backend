package com.swygbr.backend.practice.domain;

import com.swygbr.backend.user.domain.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "PracticeEpisodeComplete")
public class PracticeEpisodeComplete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userFk")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "episodeFk")
    private PracticeEpisode episode;

    public PracticeEpisodeComplete(UserEntity user, PracticeEpisode episode) {
        this.user = user;
        this.episode = episode;
    }

}