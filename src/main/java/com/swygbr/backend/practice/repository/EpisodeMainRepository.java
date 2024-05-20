package com.swygbr.backend.practice.repository;

import com.swygbr.backend.practice.entity.EpisodeMain;
import com.swygbr.backend.practice.entity.EpisodeMainPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeMainRepository extends JpaRepository<EpisodeMain, EpisodeMainPk> {
    List<EpisodeMain> findByCharacterId(String characterId);
}
