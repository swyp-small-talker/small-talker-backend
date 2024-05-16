package com.swygbr.backend.controller;

import com.swygbr.backend.entity.*;
import com.swygbr.backend.repository.*;
import com.swygbr.backend.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    // 캐릭터 관련 API
    @GetMapping("/characters")
    public List<CharacterMain> getAllCharacters() {
        return practiceService.getAllCharacters();
    }

    @GetMapping("/characters/{characterId}")
    public ResponseEntity<CharacterMain> getCharacterById(@PathVariable String characterId) {
        CharacterMain character = practiceService.getCharacterById(characterId);
        return character != null ? ResponseEntity.ok(character) : ResponseEntity.notFound().build();
    }

    @GetMapping("/characters/{characterId}/infos")
    public ResponseEntity<List<CharacterInfo>> getCharacterInfos(@PathVariable String characterId) {
        List<CharacterInfo> characterInfos = practiceService.getCharacterInfos(characterId);
        return ResponseEntity.ok(characterInfos);
    }

    // 에피소드 관련 API
    @GetMapping("/episodes")
    public List<EpisodeMain> getAllEpisodes() {
        return practiceService.getAllEpisodes();
    }

    @GetMapping("/episodes/{episodeId}")
    public ResponseEntity<EpisodeMain> getEpisodeById(@PathVariable String episodeId) {
        EpisodeMain episode = practiceService.getEpisodeById(episodeId);
        return episode != null ? ResponseEntity.ok(episode) : ResponseEntity.notFound().build();
    }

    @GetMapping("/episodes/{episodeId}/dialogs")
    public ResponseEntity<List<EpisodeDialog>> getEpisodeDialogs(@PathVariable String episodeId) {
        List<EpisodeDialog> episodeDialogs = practiceService.getEpisodeDialogs(episodeId);
        return ResponseEntity.ok(episodeDialogs);
    }

    @GetMapping("/episodes/{episodeId}/rewards")
    public ResponseEntity<List<EpisodeReward>> getEpisodeRewards(@PathVariable String episodeId) {
        List<EpisodeReward> episodeRewards = practiceService.getEpisodeRewards(episodeId);
        return ResponseEntity.ok(episodeRewards);
    }

}


