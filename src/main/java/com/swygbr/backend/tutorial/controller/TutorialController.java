package com.swygbr.backend.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swygbr.backend.tutorial.service.TutorialService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tutorial")
public class TutorialController {

  @Autowired
  private TutorialService tutorialService;

  // 튜토리얼 상태 조회(완료여부, 시작 위치)
  @GetMapping
  public ResponseEntity<?> getTutorialStatus() {
    return ResponseEntity.ok(tutorialService.findTutorialStatus());
  }

  // 튜토리얼 조회
  @GetMapping("/{tutorialId}")
  public ResponseEntity<?> getTutorial(@PathVariable(name = "tutorialId") Long tutorialId) {
    return ResponseEntity.ok(tutorialService.findTutorialById(tutorialId));
  }

  // // 튜토리얼 채팅 시작
  // @GetMapping("/chat/{chatId}")
  // public ResponseEntity<?> getTutorialStart(@PathVariable BotType botType) {
  // return ResponseEntity.ok(tutorialService.findTutorialStartMessage(botType));
  // }

  // // 튜토리얼 선택지 제출
  // @PostMapping("/{tutorialId}/choice")
  // public ResponseEntity<?> postTutorialSubmit(@PathVariable BotType botType,
  // @PathVariable long tutorialId) {
  // return ResponseEntity.ok(tutorialService.postTutorialChoice(tutorialId));
  // }

  // // 튜토리얼 입력 제출
  // @PostMapping("/{tutorialId}/insert")
  // public ResponseEntity<?> postTutorialInsert(@PathVariable long tutorialId,
  // @RequestBody RequestTutorialInsertDto request) {
  // BotType botType = BotType.GENIE;
  // return
  // ResponseEntity.ok(tutorialService.postTutorialInsertUserName(tutorialId,
  // request));
  // }
}