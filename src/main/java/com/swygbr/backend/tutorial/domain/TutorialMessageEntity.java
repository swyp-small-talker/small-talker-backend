package com.swygbr.backend.tutorial.domain;

import java.util.List;

import com.swygbr.backend.tutorial.enums.TutorialMessageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "TutorialMessage")
public class TutorialMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TutorialMessageType messageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorialFk", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private TutorialEntity tutorial;

    @OneToOne(mappedBy = "message", fetch = FetchType.EAGER)
    private TutorialMessageTextEntity text;

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    private List<TutorialMessageChoiceEntity> choices;

    @OneToOne(mappedBy = "message", fetch = FetchType.EAGER)
    private TutorialMessageInputEntity input;
}
