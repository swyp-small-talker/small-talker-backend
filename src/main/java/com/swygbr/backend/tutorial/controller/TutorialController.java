package com.swygbr.backend.tutorial.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swygbr.backend.tutorial.dto.RequestTutorialInsertDto;
import com.swygbr.backend.tutorial.dto.TutorialDto;
import com.swygbr.backend.tutorial.enums.TutorialType;
import com.swygbr.backend.tutorial.service.TutorialService;

@RestController
@RequestMapping("/tutorial")
public class TutorialController {

  @Autowired
  private TutorialService tutorialService;

  // 현재 인증된 사용자의 튜토리얼 완료 여부 조회
  @GetMapping
  public ResponseEntity<?> getAllTutorial() {
    return ResponseEntity.ok(tutorialService.findAllTutorial());
  }

  // 튜토리얼 채팅 시작
  @GetMapping("/chat/{chatId}")
  public ResponseEntity<?> getTutorialStart(@PathVariable BotType botType) {
    return ResponseEntity.ok(tutorialService.findTutorialStartMessage(botType));
  }

  // 튜토리얼 조회
  @GetMapping("/{tutorialId}")
  public ResponseEntity<?> getTutorial(@PathVariable long tutorialId) {

    TutorialDto dto = tutorialService.findTutorial(tutorialId);

    if (TutorialType.LAST_MESSAGE == dto.getTutorialType()) {
      Link userCardLink = linkTo(methodOn(UserCardController.class).getUserCard(dto.getLastMessage().userCardId()))
          .withRel("userCard");
      dto.add(userCardLink);
    } else if (TutorialType.MESSAGE == dto.getTutorialType() || TutorialType.QUESTION == dto.getTutorialType()) {
      Link nextLink = linkTo(methodOn(TutorialController.class).getTutorial(dto.getChildId()))
          .withRel(IanaLinkRelations.NEXT);
      dto.add(nextLink);
    }
    return ResponseEntity.ok(dto);
  }

  // 튜토리얼 선택지 제출
  @PostMapping("/{tutorialId}/choice")
  public ResponseEntity<?> postTutorialSubmit(@PathVariable BotType botType, @PathVariable long tutorialId) {
    return ResponseEntity.ok(tutorialService.postTutorialChoice(tutorialId));
  }

  // 튜토리얼 입력 제출
  @PostMapping("/{tutorialId}/insert")
  public ResponseEntity<?> postTutorialInsert(@PathVariable long tutorialId,
      @RequestBody RequestTutorialInsertDto request) {
    BotType botType = BotType.GENIE;
    return ResponseEntity.ok(tutorialService.postTutorialInsertUserName(tutorialId, request));
  }
}