package com.swygbr.backend.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.swygbr.backend.tutorial.dto.RequestTutorialChoiceDto;
import com.swygbr.backend.tutorial.dto.ResponseTutorialChoiceDto;
import com.swygbr.backend.tutorial.dto.TutorialDto;
import com.swygbr.backend.tutorial.dto.TutorialStatusDto;
import com.swygbr.backend.tutorial.service.TutorialService;

@RestController
@RequestMapping("/tutorial")
public class TutorialController {

  @Autowired
  private TutorialService tutorialService;

  // 튜토리얼 상태 조회(완료여부, 시작 위치)
  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<TutorialStatusDto>>> getTutorialStatus(
      @AuthenticationPrincipal JwtUserPrincipal userPrincipal) {
    return ResponseEntity.ok(tutorialService.findTutorialStatus(userPrincipal.getUserId()));
  }

  // 튜토리얼 조회
  @GetMapping("/{tutorialId}")
  public ResponseEntity<EntityModel<TutorialDto>> getTutorial(@PathVariable(name = "tutorialId") Long tutorialId) {
    return ResponseEntity.ok(tutorialService.findTutorialById(tutorialId));
  }

  // 튜토리얼 선택지 제출
  @PostMapping("/complete")
  public ResponseEntity<EntityModel<ResponseTutorialChoiceDto>> postComplete(
      @RequestBody RequestTutorialChoiceDto request) {
    EntityModel<ResponseTutorialChoiceDto> completeModel = tutorialService.completeTutorialWithChoice(request);
    return ResponseEntity.ok(completeModel);
  }
}