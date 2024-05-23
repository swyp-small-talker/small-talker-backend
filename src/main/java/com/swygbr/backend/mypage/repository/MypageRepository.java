package com.swygbr.backend.mypage.repository;

import com.swygbr.backend.mypage.dto.MypageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface MypageRepository extends JpaRepository<MypageDTO, Long> {

    @Query(value = """
    SELECT 
        u.id, 
        u.name, 
        ROUND(COUNT(CASE WHEN em.episode_complete_yn = 'Y' THEN 1 END) / COUNT(*) * 100, 2) AS completion_rate
    FROM users u 
    LEFT JOIN TB_EPISODE_MAIN em ON u.id = em.user_id
    WHERE u.id = :userId
    """, nativeQuery = true)
    Optional<MypageDTO> findMyPageInfo(@Param("userId") Long userId);

}
