package com.swygbr.backend.practice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "PracticeSkill")
public class PracticeSkill {
    @Id
    @Column(length = 255)
    private String id;

    @Column(length = 255)
    private String content;

    @ManyToOne
    @JoinColumn(name = "episodeFk")
    private PracticeEpisode episode;
}
