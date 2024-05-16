package com.swygbr.backend.service;

import com.swygbr.backend.entity.*;
import com.swygbr.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<CharacterMain> getAllCharacters() {
        return characterMainRepository.findAll();
    }

    public CharacterMain getCharacterById(String characterId) {
        return characterMainRepository.findById(characterId).orElse(null);
    }

    public List<CharacterInfo> getCharacterInfos(String characterId) {
        return characterInfoRepository.findByCharacterId(characterId);
    }

    // 에피소드 관련 로직
    public List<EpisodeMain> getAllEpisodes() {
        return episodeMainRepository.findAll();
    }

    public EpisodeMain getEpisodeById(String episodeId) {
        return episodeMainRepository.findById(new EpisodeMainPk(episodeId, "CH001")).orElse(null); // 예시: 첫 번째 캐릭터의 에피소드로 조회
    }

    public List<EpisodeDialog> getEpisodeDialogs(String episodeId) {
        return episodeDialogRepository.findByEpisodeId(episodeId);
    }

    public List<EpisodeReward> getEpisodeRewards(String episodeId) {
        return episodeRewardRepository.findByEpisodeId(episodeId);
    }

}
