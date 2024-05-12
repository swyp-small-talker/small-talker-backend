package com.swygbr.backend.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swygbr.backend.tutorial.domain.TutorialMessageTextEntity;

@Repository
public interface TutorialMessageTextRepository
        extends JpaRepository<TutorialMessageTextEntity, Long> {
}
