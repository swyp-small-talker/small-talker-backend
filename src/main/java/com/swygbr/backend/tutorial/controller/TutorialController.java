package com.swygbr.backend.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swygbr.backend.tutorial.dto.RequestTutorialInputDto;
import com.swygbr.backend.tutorial.service.TutorialService;

import jakarta.validation.Valid;

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

  // 튜토리얼 입력
  @PostMapping("/input")
  public ResponseEntity<?> postInput(@RequestBody @Valid RequestTutorialInputDto request) {
    tutorialService.submitInput(request);
    return ResponseEntity.ok().build();
  }
  
}