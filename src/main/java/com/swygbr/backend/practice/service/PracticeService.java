package com.swygbr.backend.practice.service;

import com.swygbr.backend.practice.entity.*;
import com.swygbr.backend.practice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PracticeService {

    @Autowired
    private CharacterMainRepository characterMainRepository;
    @Autowired
    private EpisodeMainRepository episodeMainRepository;
    @Autowired
    private CharacterInfoRepository characterInfoRepository;
    @Autowired
    private EpisodeDialogRepository episodeDialogRepository;
    @Autowired
    private EpisodeRewardRepository episodeRewardRepository;

    // 캐릭터 관련 로직
    public List<CharacterMain> getCharacterList() {
        return characterMainRepository.findAll();
    }

    public CharacterMain getCharacterById(String characterId) {
        return characterMainRepository.findById(characterId).orElse(null);
    }

    public List<CharacterInfo> getCharacterInfos(String characterId) {
        return characterInfoRepository.findByCharacterId(characterId);
    }

    public List<Object[]> getCharacterKeywords(String characterId) {
        return characterInfoRepository.findCharacterInfoWithDetailsByCharacterId(characterId);
    }

    // 에피소드 관련 로직
    public List<EpisodeMain> getAllEpisodes() {
        return episodeMainRepository.findAll();
    }

    public EpisodeMain getEpisodeById(String episodeId) {
        return episodeMainRepository.findByEpisodeId(episodeId).orElse(null);
    }

    public List<EpisodeMain> getEpisodesByCharacterId(String characterId, String userId) {
        return episodeMainRepository.findByCharacterIdAndUserId(characterId, userId);
    }

    public Optional<EpisodeDialog> findInitialDialogByEpisodeId(String episodeId) {
        return episodeDialogRepository.findByEpisodeIdAndParentDialogIdIsNull(episodeId);
    }

    public List<EpisodeDialog> findChildrenByParentDialogId(String chatId) {
        return episodeDialogRepository.findChildrenByParentDialogId(chatId);
    }

}
