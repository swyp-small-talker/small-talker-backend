package com.swygbr.backend.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swygbr.backend.practice.domain.PracticeEpisode;

@Repository
public interface PracticeEpisodeRepository extends JpaRepository<PracticeEpisode, String> {
    List<PracticeEpisode> findByCharacter_Id(String characterId);

    @Query("SELECT CASE WHEN COUNT(ec) > 0 THEN TRUE ELSE FALSE END " +
            "FROM PracticeEpisodeComplete ec " +
            "WHERE ec.episode.id = :episodeId AND ec.user.id = :userId")
    boolean isEpisodeCompleted(@Param("episodeId") String episodeId, @Param("userId") Long userId);

}