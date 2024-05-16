package com.swygbr.backend.controller;

import com.swygbr.backend.entity.CharacterInfo;
import com.swygbr.backend.entity.CharacterMain;
import com.swygbr.backend.entity.EpisodeMain;
import com.swygbr.backend.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    // 대화 연습 목록 조회
    @GetMapping
    public ResponseEntity<?> getPracticeList() {
        // TODO: 연습 목록 조회 로직 구현 (practiceService.getPracticeList() 호출)
        return ResponseEntity.ok("대화 연습 목록 조회");
    }

    // 대화 연습 캐릭터 목록 조회
    @GetMapping("/character")
    public ResponseEntity<?> getCharacterList() {
        List<CharacterMain> characterList = practiceService.getCharacterList();
        return ResponseEntity.ok(characterList);
    }

    // 대화 연습 캐릭터 및 완료 여부 조회
    @GetMapping("/character/{characterId}")
    public ResponseEntity<?> getCharacter(@PathVariable String characterId) {
        CharacterMain character = practiceService.getCharacterById(characterId);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }

        // TODO: 완료 여부 조회 로직 추가
        return ResponseEntity.ok(character);
    }

    // 대화 연습 캐릭터의 에피소드 목록 및 완료 여부 조회
    @GetMapping("/character/{characterId}/episode")
    public ResponseEntity<?> getCharacterEpisodes(@PathVariable String characterId) {
        List<EpisodeMain> episodeList = practiceService.getEpisodesByCharacterId(characterId);
        if (episodeList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // TODO: 각 에피소드의 완료 여부 정보 추가
        return ResponseEntity.ok(episodeList);
    }

    // 대화 연습 캐릭터의 키워드 목록 조회
    @GetMapping("/character/{characterId}/keyword")
    public ResponseEntity<List<Map<String, Object>>> getCharacterKeywords(@PathVariable String characterId) {
        List<Object[]> results = practiceService.getCharacterKeywords(characterId);
        List<Map<String, Object>> characterKeywords = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> characterKeyword = new HashMap<>();
            characterKeyword.put("infoId", result[0]);
            characterKeyword.put("characterId", result[1]);
            characterKeyword.put("infoCategoryNm", result[2]);
            characterKeyword.put("infoDetailNm", result[3]);
            characterKeywords.add(characterKeyword);
        }

        return ResponseEntity.ok(characterKeywords);
    }

    // 대화 연습 에피소드 조회
    @GetMapping("/episode/{episodeId}")
    public ResponseEntity<?> getEpisode(@PathVariable String episodeId) {
        EpisodeMain episode = practiceService.getEpisodeById(episodeId);
        return episode != null ? ResponseEntity.ok(episode) : ResponseEntity.notFound().build();
    }

    // 대화 연습 에피소드의 채팅 시작
    @GetMapping("/episode/{episodeId}/chat")
    public ResponseEntity<?> startEpisodeChat(@PathVariable String episodeId) {
        // TODO: 대화 연습 채팅 시작 로직 구현 (예: 채팅방 생성, 첫 대화 출력 등)
        return ResponseEntity.ok("대화 연습 에피소드의 채팅 시작");
    }

    // 대화 연습 채팅 조회
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<?> getChat(@PathVariable String chatId) {
        // TODO: 대화 연습 채팅 조회 로직 구현
        return ResponseEntity.ok("대화 연습 채팅 조회");
    }

    // 대화 연습 채팅 선택지 제출
    @GetMapping("/chat/{chatId}/choice")
    public ResponseEntity<?> submitChatChoice(@PathVariable String chatId) {
        // TODO: 대화 연습 채팅 선택지 제출 로직 구현
        return ResponseEntity.ok("대화 연습 채팅 선택지 제출");
    }

    // 대화 스킬 조회
    @GetMapping("/skill/{skillId}")
    public ResponseEntity<?> getSkill(@PathVariable String skillId) {
        // TODO: 대화 스킬 조회 로직 구현
        return ResponseEntity.ok("대화 스킬 조회");
    }

    // 대화 키워드 조회
    @GetMapping("/keyword/{keywordId}")
    public ResponseEntity<?> getKeyword(@PathVariable String keywordId) {
        // TODO: 대화 키워드 조회 로직 구현
        return ResponseEntity.ok("대화 키워드 조회");
    }
}



