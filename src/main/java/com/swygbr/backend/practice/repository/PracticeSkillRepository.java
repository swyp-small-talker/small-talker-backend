package com.swygbr.backend.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.swygbr.backend.practice.domain.PracticeSkill;

public interface PracticeSkillRepository extends JpaRepository<PracticeSkill, String> {
    // 1. 특정 캐릭터의 에피소드의 전체 스킬 개수
    @Query("""
            SELECT COUNT(s)
            FROM PracticeEpisode ep
            JOIN ep.skills s
            WHERE ep.character.id = :characterId
            """)
    int countTotalSkillsByCharacterId(@Param("characterId") String characterId);

    // 2. 회원이 획득한 특정 캐릭터의 에피소드의 스킬 개수
    @Query("""
            SELECT COUNT(s)
            FROM PracticeEpisodeComplete ec
            JOIN ec.episode ep
            JOIN ep.skills s
            WHERE ec.user.id = :userId AND ep.character.id = :characterId
            """)
    int countCompletedSkillsByUserIdAndCharacterId(@Param("userId") Long userId,
            @Param("characterId") String characterId);

    // 3. 회원이 획득한 특정 캐릭터의 에피소드의 스킬 목록
    @Query("""
            SELECT s
            FROM PracticeEpisodeComplete ec
            JOIN ec.episode ep
            JOIN ep.skills s
            WHERE ec.user.id = :userId AND ep.character.id = :characterId
            """)
    List<PracticeSkill> findAcquiredSkillsByUserIdAndCharacterId(@Param("userId") Long userId,
            @Param("characterId") String characterId);

    // 회원이 획득한 모든 스킬 조회
    @Query("""
            SELECT s
            FROM PracticeEpisodeComplete ec
            JOIN ec.episode ep
            JOIN ep.skills s
            WHERE ec.user.id = :userId
            """)
    List<PracticeSkill> findAllSkillsByUserId(@Param("userId") Long userId);
}
