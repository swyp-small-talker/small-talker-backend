package com.swygbr.backend.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swygbr.backend.tutorial.domain.TutorialMessageChoiceEntity;

@Repository
public interface TutorialMessageChoiceRepository
        extends JpaRepository<TutorialMessageChoiceEntity, Long> {
}
