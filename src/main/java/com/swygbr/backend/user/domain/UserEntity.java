package com.swygbr.backend.user.domain;

import java.util.Date;

import com.swygbr.backend.tutorial.domain.UserCardEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userCardFk")
    private UserCardEntity userCard;

    @Column(nullable = false)
    private Boolean completeProfileTypeTutorial = false;

    @Column(nullable = false)
    private Boolean completeUserCardTypeTutorial = false;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;

    public void assignUserCard(UserCardEntity userCardEntity) {
        this.userCard = userCardEntity;
        this.completeUserCardTypeTutorial = true;
    }

    public void setName(String name) {
        this.name = name;
        this.completeProfileTypeTutorial = true;
    }

    public boolean completeTalk() {
        return true;
    }
}