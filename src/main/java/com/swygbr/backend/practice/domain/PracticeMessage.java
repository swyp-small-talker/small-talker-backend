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
@Table(name = "PracticeMessage")
public class PracticeMessage {
    @Id
    @Column(length = 255)
    private String id;

    @Column(length = 255)
    private String actor;

    @Column(length = 255)
    private String messageType;

    @Column(length = 255)
    private String content;

    @ManyToOne
    @JoinColumn(name = "episodeFk")
    private PracticeEpisode episode;

    @ManyToOne
    @JoinColumn(name = "parentFk")
    private PracticeMessage parent;

    @OneToMany(mappedBy = "parent")
    private List<PracticeMessage> childList;
}
