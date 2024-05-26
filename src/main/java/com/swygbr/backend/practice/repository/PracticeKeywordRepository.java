package com.swygbr.backend.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.swygbr.backend.practice.domain.PracticeKeyword;

public interface PracticeKeywordRepository extends JpaRepository<PracticeKeyword, String> {
    // 특정 캐릭터의 에피소드의 전체 키워드 개수
    @Query("SELECT COUNT(k) FROM PracticeEpisode ep " +
            "JOIN ep.keywords k " +
            "WHERE ep.character.id = :characterId")
    int countTotalKeywordsByCharacterId(@Param("characterId") String characterId);

    // 특정 캐릭터의 에피소드의 획득한 키워드 목록
    @Query("SELECT k FROM PracticeEpisodeComplete ec " +
            "JOIN ec.episode ep " +
            "JOIN ep.keywords k " +
            "WHERE ec.user.id = :userId AND ep.character.id = :characterId")
    List<PracticeKeyword> findAcquiredKeywordsByUserIdAndCharacterId(@Param("userId") Long userId,
            @Param("characterId") String characterId);

}
