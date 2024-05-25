package com.swygbr.backend.practice.repository;

import com.swygbr.backend.practice.entity.EpisodeReward;
import com.swygbr.backend.practice.entity.EpisodeRewardPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRewardRepository extends JpaRepository<EpisodeReward, EpisodeRewardPk> {
    List<EpisodeReward> findByEpisodeId(String episodeId);
}
