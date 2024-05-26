package com.swygbr.backend.practice.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "PracticeEpisode")
public class PracticeEpisode {
    @Id
    @Column(length = 255)
    private String id;

    @Column(length = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "characterFk")
    private PracticeCharacter character;

    @OneToMany(mappedBy = "episode")
    private List<PracticeEpisodeComplete> complete;

    @OneToMany(mappedBy = "episode")
    private List<PracticeKeyword> keywords;

    @OneToMany(mappedBy = "episode")
    private List<PracticeSkill> skills; // 추가된 필드
}
