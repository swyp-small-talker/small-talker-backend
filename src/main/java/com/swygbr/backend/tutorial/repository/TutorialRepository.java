package com.swygbr.backend.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swygbr.backend.tutorial.domain.TutorialEntity;

@Repository
public interface TutorialRepository extends JpaRepository<TutorialEntity, Long> {
  List<TutorialEntity> findByIsStartTrue();
  // Optional<TutorialEntity> findByParentIsNullAndBotType(BotType botType);

  // Optional<TutorialEntity> findByBotTypeAndId(BotType botType, Long id);

}