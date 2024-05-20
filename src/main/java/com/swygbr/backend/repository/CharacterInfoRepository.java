package com.swygbr.backend.repository;

import com.swygbr.backend.practice.entity.CharacterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CharacterInfoRepository extends JpaRepository<CharacterInfo, String> {
    List<CharacterInfo> findByCharacterId(String characterId);

    @Query(value = "SELECT ci.info_id, ci.character_id, ci.info_category_nm, cid.info_detail_nm " +
            "FROM TB_CHARACTER_INFO ci " +
            "LEFT JOIN TB_CHARACTER_INFO_DETAIL cid ON ci.info_id = cid.info_id " +
            "WHERE ci.character_id = :characterId", nativeQuery = true)
    List<Object[]> findCharacterInfoWithDetailsByCharacterId(@Param("characterId") String characterId);

}