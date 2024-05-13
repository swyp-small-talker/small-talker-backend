package com.swygbr.backend.tutorial.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.tutorial.domain.TutorialEntity;
import com.swygbr.backend.tutorial.dto.RequestTutorialInsertDto;
import com.swygbr.backend.tutorial.dto.TutorialCompletionDto;
import com.swygbr.backend.tutorial.dto.TutorialDto;
import com.swygbr.backend.tutorial.enums.BotType;
import com.swygbr.backend.tutorial.enums.InsertType;
import com.swygbr.backend.tutorial.enums.TutorialType;
import com.swygbr.backend.tutorial.repository.TutorialRepository;
import com.swygbr.backend.user.domain.UserEntity;
import com.swygbr.backend.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class TutorialService {
    private final TutorialRepository tutorialRepository;
    private final UserRepository userRepository;

    public TutorialCompletionDto checkCompletion() {
        UserEntity userEntity = getUserFromEmail();
        BotType completedByBotType = userEntity.getCompletedByBotType();

        return TutorialCompletionDto.builder()
                .genieComplete(BotType.GENIE == completedByBotType)
                .jasmineComplete(BotType.JASMINE == completedByBotType)
                .build();
    }

    public TutorialDto findTutorialStartMessage(BotType botType) {
        UserEntity userEntity = getUserFromEmail();

        if (userEntity.getCompletedByBotType() == botType) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이미 완료한 튜토리얼 입니다.");
        }

        TutorialEntity entity = tutorialRepository.findByParentIsNullAndBotType(botType)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "튜토리얼을 찾을 수 없습니다."));

        return TutorialDto.fromEntity(entity);
    }

    public TutorialDto findTutorial(long tutorialId) {
        UserEntity userEntity = getUserFromEmail();

        var entity = tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "튜토리얼을 찾을 수 없습니다."));

        if (userEntity.getCompletedByBotType().isHigherThan(entity.getBotType())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이미 완료한 튜토리얼 입니다.");
        }

        var dto = TutorialDto.fromEntity(entity);
        dto.setChildId(entity.getChildren().get(0).getId());
        return dto;
    }

    public TutorialDto postTutorialChoice(long tutorialId) {
        UserEntity userEntity = getUserFromEmail();

        var entity = tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "튜토리얼을 찾을 수 없습니다."));

        if (userEntity.getCompletedByBotType().isHigherThan(entity.getBotType())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이미 완료한 튜토리얼 입니다.");
        }

        if (entity.getTutorialType() != TutorialType.CHOICE) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "튜토리얼 선택지 타입이 아닙니다.");
        }

        // 마지막 선택지면 user에게 user 카드 부여
        if (entity.getUserCard() != null) {
            userEntity.assignUserCard(entity.getUserCard());
        }

        // 다음 튜토리얼 조회
        var child = entity.getChildren().get(0);
        return TutorialDto.fromEntity(child);
    }

    public TutorialDto postTutorialInsertUserName(long tutorialId, RequestTutorialInsertDto request) {
        UserEntity userEntity = getUserFromEmail();
        var entity = tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "튜토리얼을 찾을 수 없습니다."));

        if (userEntity.getCompletedByBotType().isHigherThan(entity.getBotType())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이미 완료한 튜토리얼 입니다.");
        }

        if (entity.getTutorialType() != TutorialType.INSERT) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "튜토리얼 입력 타입이 아닙니다.");
        }

        if (InsertType.USER_NAME == request.insertType()) {
            userEntity.setUserName(request.content());
        }

        // 다음 튜토리얼 조회
        var child = entity.getChildren().get(0);
        return TutorialDto.fromEntity(child);
    }

    private UserEntity getUserFromEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "존재하지 않는 email입니다: " + email));
    }

}
