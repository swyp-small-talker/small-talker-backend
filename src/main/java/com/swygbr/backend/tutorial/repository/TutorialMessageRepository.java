package com.swygbr.backend.tutorial.repository;

import com.swygbr.backend.tutorial.domain.TutorialMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialMessageRepository
    extends JpaRepository<TutorialMessageEntity, Long> {
}
