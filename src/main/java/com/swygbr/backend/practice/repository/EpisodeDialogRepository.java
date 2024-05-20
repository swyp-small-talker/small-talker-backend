package com.swygbr.backend.practice.repository;

import com.swygbr.backend.practice.entity.EpisodeDialog;
import com.swygbr.backend.practice.entity.EpisodeDialogPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpisodeDialogRepository extends JpaRepository<EpisodeDialog, EpisodeDialogPk> {
    List<EpisodeDialog> findByEpisodeId(String episodeId);

    @Query(value = "SELECT * FROM TB_EPISODE_DIALOG " +
            "WHERE parent_dialog_id = :chatId", nativeQuery = true
    )
    List<EpisodeDialog> findChildrenByParentDialogId(String chatId);

    Optional<EpisodeDialog> findByEpisodeIdAndParentDialogIdIsNull(String episodeId);

}
