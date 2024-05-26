package com.swygbr.backend.practice.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PracticeCharacter")
public class PracticeCharacter {
    @Id
    @Column(length = 255)
    private String id;

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String type;

    @OneToMany(mappedBy = "character")
    private List<PracticeEpisode> episodeList;
}