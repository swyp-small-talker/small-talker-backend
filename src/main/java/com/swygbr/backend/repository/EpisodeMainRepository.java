package com.swygbr.backend.repository;

import com.swygbr.backend.entity.EpisodeMain;
import com.swygbr.backend.entity.EpisodeMainPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeMainRepository extends JpaRepository<EpisodeMain, EpisodeMainPk> {
}
