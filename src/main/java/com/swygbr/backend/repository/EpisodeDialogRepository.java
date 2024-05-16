package com.swygbr.backend.repository;

import com.swygbr.backend.entity.EpisodeDialog;
import com.swygbr.backend.entity.EpisodeDialogPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeDialogRepository extends JpaRepository<EpisodeDialog, EpisodeDialogPk> {
    List<EpisodeDialog> findByEpisodeId(String episodeId);
}
