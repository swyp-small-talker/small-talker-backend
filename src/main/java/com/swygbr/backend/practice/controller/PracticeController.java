package com.swygbr.backend.practice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.login.auth.JwtUserPrincipal;
import com.swygbr.backend.practice.entity.CharacterMain;
import com.swygbr.backend.practice.entity.EpisodeDialog;
import com.swygbr.backend.practice.entity.EpisodeMain;
import com.swygbr.backend.practice.service.PracticeService;

@RestController
@RequestMapping("/practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    // 대화 연습 목록 조회
    @GetMapping
    public ResponseEntity<?> getPracticeList() {
        List<EpisodeMain> episodeList = practiceService.getAllEpisodes();
        return ResponseEntity.ok(episodeList);
    }

    // 대화 연습 캐릭터 목록 조회
    @GetMapping("/character")
    public ResponseEntity<?> getCharacterList() {
        List<CharacterMain> characterList = practiceService.getCharacterList();
        return ResponseEntity.ok(characterList);
    }

    // 대화 연습 캐릭터 목록 조회
    @GetMapping("/character/{characterId}")
    public ResponseEntity<?> getCharacter(@PathVariable String characterId) {
        CharacterMain character = practiceService.getCharacterById(characterId);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(character);
    }

    // 대화 연습 캐릭터의 에피소드 목록 및 완료 여부 조회
    @GetMapping("/character/{characterId}/episode")
    public ResponseEntity<?> getCharacterEpisodes(@PathVariable String characterId,
            @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {
        List<EpisodeMain> episodeList = practiceService.getEpisodesByCharacterId(characterId,
                userPrincipal.getUserId());
        if (episodeList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(episodeList);
    }

    // 대화 연습 캐릭터의 키워드 목록 조회
    @GetMapping("/character/{characterId}/keyword")
    public ResponseEntity<List<Map<String, Object>>> getCharacterKeywords(@PathVariable String characterId) {
        List<Object[]> results = practiceService.getCharacterKeywords(characterId);
        List<Map<String, Object>> characterKeywords = new ArrayList<>();

        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
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
        EpisodeDialog dialog = practiceService.findInitialDialogByEpisodeId(episodeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "채팅을 찾을 수 없습니다."));
        return ResponseEntity.ok(dialog);
    }

    // 대화 연습 채팅 조회
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<?> getChat(@PathVariable String chatId) {
        List<EpisodeDialog> episodeDialogList = practiceService.findChildrenByParentDialogId(chatId);
        return episodeDialogList.isEmpty() ? ResponseEntity.ok(new HashMap<>(Map.of("last", true)))
                : ResponseEntity.ok(episodeDialogList);
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
