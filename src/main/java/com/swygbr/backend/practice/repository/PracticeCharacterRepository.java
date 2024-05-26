package com.swygbr.backend.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swygbr.backend.practice.domain.PracticeCharacter;
import com.swygbr.backend.practice.domain.PracticeKeyword;

@Repository
public interface PracticeCharacterRepository extends JpaRepository<PracticeCharacter, String> {
    @Query("""
            SELECT CASE WHEN
            SUM(CASE WHEN ec.id IS NOT NULL THEN 1 ELSE 0 END) = COUNT(ep.id)
            THEN TRUE ELSE FALSE END
            FROM PracticeEpisode ep
            LEFT JOIN PracticeEpisodeComplete ec ON ep.id = ec.episode.id AND ec.user.id = :userId
            WHERE ep.character.id = :characterId
            """)
    boolean isCharacterCompleted(String characterId, Long userId);

}