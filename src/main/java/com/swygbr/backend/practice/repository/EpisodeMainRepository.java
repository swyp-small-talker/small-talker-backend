package com.swygbr.backend.practice.repository;

import com.swygbr.backend.practice.entity.EpisodeMain;
import com.swygbr.backend.practice.entity.EpisodeMainPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeMainRepository extends JpaRepository<EpisodeMain, EpisodeMainPk> {

    List<EpisodeMain> findByCharacterIdAndUserId(String characterId, Long userId);

    Optional<EpisodeMain> findByEpisodeId(String episodeId);
}
