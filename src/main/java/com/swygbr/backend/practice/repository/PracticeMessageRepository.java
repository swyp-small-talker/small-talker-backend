package com.swygbr.backend.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swygbr.backend.practice.domain.PracticeMessage;

@Repository
public interface PracticeMessageRepository extends JpaRepository<PracticeMessage, String> {

}
