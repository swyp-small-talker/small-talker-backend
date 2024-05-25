package com.swygbr.backend.practice.repository;

import com.swygbr.backend.practice.entity.CharacterMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterMainRepository extends JpaRepository<CharacterMain, String> {
}
