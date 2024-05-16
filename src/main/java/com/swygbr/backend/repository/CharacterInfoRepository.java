package com.swygbr.backend.repository;

import com.swygbr.backend.entity.CharacterInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterInfoRepository extends JpaRepository<CharacterInfo, String> {
    List<CharacterInfo> findByCharacterId(String characterId);
}