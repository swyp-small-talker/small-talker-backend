package com.swygbr.backend.tutorial.domain;

import java.util.List;

import com.swygbr.backend.tutorial.enums.TutorialType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Tutorial")
public class TutorialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TutorialType tutorialType;

    @Column(name = "isStart", nullable = false)
    private Boolean isStart;

    @OneToMany(mappedBy = "tutorial", fetch = FetchType.EAGER)
    List<TutorialMessageEntity> tutorialMessages;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nextTutorialFk", referencedColumnName = "id", insertable = false, updatable = false)
    private TutorialEntity nextTutorial;
}
