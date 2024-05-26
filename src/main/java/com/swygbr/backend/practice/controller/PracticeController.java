package com.swygbr.backend.practice.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swygbr.backend.login.auth.JwtUserPrincipal;
import com.swygbr.backend.practice.dto.CharacterKeywordResponseDto;
import com.swygbr.backend.practice.dto.CharacterResponseDto;
import com.swygbr.backend.practice.dto.CharacterSkillResponseDto;
import com.swygbr.backend.practice.dto.EpisodeCompleteRequestDto;
import com.swygbr.backend.practice.dto.EpisodeCompleteResponseDto;
import com.swygbr.backend.practice.dto.EpisodeResponseDto;
import com.swygbr.backend.practice.dto.MessageResponseDto;
import com.swygbr.backend.practice.service.PracticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/practice")
public class PracticeController {
    private final PracticeService practiceService;

    // 대화 연습 캐릭터 목록 조회
    @GetMapping("/character")
    public ResponseEntity<CollectionModel<EntityModel<CharacterResponseDto>>> getCharacterList(
            @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {
        CollectionModel<EntityModel<CharacterResponseDto>> dtoList = practiceService
                .getCharacterList(userPrincipal.getUserId());
        return ResponseEntity.ok(dtoList);
    }

    // 대화 연습 캐릭터 조회
    @GetMapping("/character/{characterId}")
    public ResponseEntity<EntityModel<CharacterResponseDto>> getCharacter(@PathVariable String characterId,
            @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {

        EntityModel<CharacterResponseDto> dto = practiceService.getCharacter(characterId, userPrincipal.getUserId());
        return ResponseEntity.ok(dto);
    }

    // 대화 연습 캐릭터의 에피소드 목록 조회
    @GetMapping("/character/{characterId}/episode")
    public ResponseEntity<CollectionModel<EntityModel<EpisodeResponseDto>>> getCharacterEpisode(
            @PathVariable String characterId,
            @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {

        CollectionModel<EntityModel<EpisodeResponseDto>> dtoList = practiceService.getCharacterEpisode(characterId,
                userPrincipal.getUserId());
        return ResponseEntity.ok(dtoList);
    }

    // 대화 연습 캐릭터의 사용자가 획득한 키워드 목록 조회
    @GetMapping("/character/{characterId}/keyword")
    public ResponseEntity<EntityModel<CharacterKeywordResponseDto>> getCharacterKeywords(
            @PathVariable String characterId,
            @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {
        EntityModel<CharacterKeywordResponseDto> dto = practiceService.getCharacterKeywords(characterId,
                userPrincipal.getUserId());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/character/{characterId}/skill")
    public ResponseEntity<EntityModel<CharacterSkillResponseDto>> getCharacterSkill(
            @PathVariable String characterId,
            @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {
        EntityModel<CharacterSkillResponseDto> dto = practiceService.getCharacterSkills(characterId,
                userPrincipal.getUserId());
        return ResponseEntity.ok(dto);
    }

    // 대화 연습 에피소드 완료 제출
    @PostMapping("/episode/{episodeId}/complete")
    public ResponseEntity<EntityModel<EpisodeCompleteResponseDto>> postEpisodeComplete(@PathVariable String episodeId,
            @RequestBody EpisodeCompleteRequestDto request,
            @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {

        EntityModel<EpisodeCompleteResponseDto> dto = practiceService.postEpisodeComplete(episodeId,
                userPrincipal.getUserId(), request);

        return ResponseEntity.ok(dto);
    }

    // 대화 연습 메시지 조회
    @GetMapping("/message/{messageId}")
    public ResponseEntity<EntityModel<MessageResponseDto>> getMessage(@PathVariable String messageId) {
        EntityModel<MessageResponseDto> dto = practiceService.getMessage(messageId);
        return ResponseEntity.ok(dto);
    }

}
