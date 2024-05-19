package com.swygbr.backend.tutorial.repository;

import com.swygbr.backend.tutorial.domain.UserCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardRepository
        extends JpaRepository<UserCardEntity, Long> {
    UserCardEntity findByTitle(String title);
}
