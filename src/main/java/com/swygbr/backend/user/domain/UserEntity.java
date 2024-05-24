package com.swygbr.backend.user.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

import com.swygbr.backend.tutorial.domain.UserCardEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@DynamicInsert
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String profile;

    @Enumerated(EnumType.STRING)
    RoleType role = RoleType.ROLE_USER;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean completeProfileTypeTutorial = false;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean completeUserCardTypeTutorial = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "userCardFk")
    private UserCardEntity userCard = null;

    public UserEntity(String email, String name, String profile, RoleType role) {
        this.email = email;
        this.name = name;
        this.profile = profile;
        this.role = role;
    }

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