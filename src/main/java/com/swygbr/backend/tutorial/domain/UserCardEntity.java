package com.swygbr.backend.tutorial.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "UserCard")
public class UserCardEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String picture;

  @Column(nullable = false, length = 20)
  private String title;

  @Column(nullable = false, length = 255)
  private String content;
}
