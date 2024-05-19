package com.swygbr.backend.tutorial.domain;

import com.swygbr.backend.tutorial.enums.TutorialMessageChoiceType;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "TutorialMessageChoice")
public class TutorialMessageChoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messageFk", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private TutorialMessageEntity message;

    @OneToOne
    @JoinColumn(name = "reactionTutorialFk", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private TutorialEntity reaction;

    @Enumerated(EnumType.STRING)
    private TutorialMessageChoiceType choiceType;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    // Constructors, getters, and setters
}
