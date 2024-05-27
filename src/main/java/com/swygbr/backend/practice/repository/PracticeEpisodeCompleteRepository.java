package com.swygbr.backend.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swygbr.backend.practice.domain.PracticeEpisodeComplete;

@Repository
public interface PracticeEpisodeCompleteRepository extends JpaRepository<PracticeEpisodeComplete, Long> {
    // 특정 사용자가 전체 에피소드를 완료했는지 확인하는 메서드
    @Query("""
            SELECT COUNT(e) = COUNT(ec)
            FROM PracticeEpisode e
            LEFT JOIN PracticeEpisodeComplete ec ON e.id = ec.episode.id AND ec.user.id = :userId
            """)
    boolean hasUserCompletedAllEpisodes(@Param("userId") Long userId);
}
