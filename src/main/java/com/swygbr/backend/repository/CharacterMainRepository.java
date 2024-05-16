package com.swygbr.backend.repository;

import com.swygbr.backend.entity.CharacterMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterMainRepository extends JpaRepository<CharacterMain, String> {
}
