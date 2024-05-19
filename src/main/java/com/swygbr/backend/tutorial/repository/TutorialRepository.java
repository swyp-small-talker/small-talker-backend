package com.swygbr.backend.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swygbr.backend.tutorial.domain.TutorialEntity;
import com.swygbr.backend.tutorial.enums.TutorialMessageType;
import com.swygbr.backend.tutorial.enums.TutorialType;

@Repository
public interface TutorialRepository extends JpaRepository<TutorialEntity, Long> {
  List<TutorialEntity> findByIsStartTrue();

  @Query("SELECT COUNT(tm) FROM TutorialEntity t JOIN t.tutorialMessages tm WHERE t.tutorialType = :tutorialType AND tm.messageType = :messageType")
  long countMessagesByTypeAndMessageType(@Param("tutorialType") TutorialType tutorialType,
      @Param("messageType") TutorialMessageType messageType);
}