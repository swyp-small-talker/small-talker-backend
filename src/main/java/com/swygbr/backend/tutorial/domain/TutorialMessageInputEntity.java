package com.swygbr.backend.tutorial.domain;

import com.swygbr.backend.tutorial.enums.TutorialMessageInputType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "TutorialMessageInput")
public class TutorialMessageInputEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "messageFk", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private TutorialMessageEntity message;

    @Enumerated(EnumType.STRING)
    private TutorialMessageInputType inputType;
}
