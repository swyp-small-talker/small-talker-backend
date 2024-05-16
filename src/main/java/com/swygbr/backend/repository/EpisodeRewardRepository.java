package com.swygbr.backend.repository;

import com.swygbr.backend.entity.EpisodeReward;
import com.swygbr.backend.entity.EpisodeRewardPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRewardRepository extends JpaRepository<EpisodeReward, EpisodeRewardPk> {
    List<EpisodeReward> findByEpisodeId(String episodeId);
}
