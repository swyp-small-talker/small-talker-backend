package com.swygbr.backend.tutorial.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.tutorial.domain.TutorialEntity;
import com.swygbr.backend.tutorial.domain.TutorialMessageChoiceEntity;
import com.swygbr.backend.tutorial.domain.UserCardEntity;
import com.swygbr.backend.tutorial.dto.RequestTutorialChoiceDto;
import com.swygbr.backend.tutorial.dto.RequestTutorialInputDto;
import com.swygbr.backend.tutorial.dto.ResponseTutorialChoiceDto;
import com.swygbr.backend.tutorial.dto.TutorialDto;
import com.swygbr.backend.tutorial.dto.TutorialStatusDto;
import com.swygbr.backend.tutorial.enums.TutorialMessageChoiceType;
import com.swygbr.backend.tutorial.enums.TutorialMessageInputType;
import com.swygbr.backend.tutorial.enums.TutorialMessageType;
import com.swygbr.backend.tutorial.enums.TutorialType;
import com.swygbr.backend.tutorial.enums.UserCardType;
import com.swygbr.backend.tutorial.repository.TutorialMessageChoiceRepository;
import com.swygbr.backend.tutorial.repository.TutorialRepository;
import com.swygbr.backend.tutorial.repository.UserCardRepository;
import com.swygbr.backend.user.domain.UserEntity;
import com.swygbr.backend.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class TutorialService {
    private final TutorialRepository tutorialRepository;
    private final TutorialMessageChoiceRepository choiceRepository;
    private final UserCardRepository userCardRepository;
    private final UserRepository userRepository;

    public List<TutorialStatusDto> findTutorialStatus() {
        UserEntity userEntity = getDefaultUser();
        List<TutorialEntity> entities = tutorialRepository.findByIsStartTrue();

        return entities.stream()
                .map((entity) -> TutorialStatusDto.fromEntity(userEntity, entity))
                .toList();
    }

    public TutorialDto findTutorialById(Long id) {
        TutorialEntity entity = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "튜토리얼을 찾을 수 없습니다."));
        return TutorialDto.fromEntity(entity);
    }

    public void submitInput(RequestTutorialInputDto request) {
        UserEntity userEntity = getDefaultUser();

        if (request.inputType() == TutorialMessageInputType.USER_NAME) {
            userEntity.updateName(request.data());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "지원하지 않는 inputType입니다.");
        }
    }

    private UserEntity getDefaultUser() {
        return userRepository.findById(1L)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "기본 유저를 찾을 수 없습니다."));
    }

    public ResponseTutorialChoiceDto submitChoice(RequestTutorialChoiceDto request) {
        UserEntity userEntity = getDefaultUser();

        if (TutorialType.USER_CARD == request.tutorialType()) {
            int reactionCount = 0;
            int myStoryCount = 0;
            int questionCount = 0;

            // tutorial 선택지 개수가 맞는지 확인
            long count = tutorialRepository.countMessagesByTypeAndMessageType(TutorialType.USER_CARD,
                    TutorialMessageType.CHOICE);
            if (count != request.userChoices().size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "선택지 개수가 일치하지않습니다.");
            }

            Iterator<Long> iterator = request.userChoices().iterator();
            Set<Long> tutorialSet = new HashSet<>();
            while (iterator.hasNext()) {
                Long choiceId = iterator.next();
                TutorialMessageChoiceEntity choiceEntity = choiceRepository.findById(choiceId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택지를 찾을 수 없습니다."));
                TutorialMessageChoiceType choiceType = choiceEntity.getChoiceType();
                tutorialSet.add(choiceEntity.getMessage().getId());
                if (TutorialMessageChoiceType.REACTION == choiceType) {
                    reactionCount++;
                } else if (TutorialMessageChoiceType.MY_STORY == choiceType) {
                    myStoryCount++;
                } else if (TutorialMessageChoiceType.QUESTION == choiceType) {
                    questionCount++;
                }
            }

            if (tutorialSet.size() != request.userChoices().size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 선택지를 제출할 수 없습니다.");
            }

            UserCardEntity userCardEntity = null;
            if (reactionCount >= 2) {
                userCardEntity = userCardRepository.findByTitle(UserCardType.REACTION_BOT.getTitle());
            } else if (myStoryCount >= 2) {
                userCardEntity = userCardRepository.findByTitle(UserCardType.TOO_MUCH_TALKER.getTitle());
            } else if (questionCount >= 2) {
                userCardEntity = userCardRepository.findByTitle(UserCardType.QUESTION_BOMBER.getTitle());
            } else {
                userCardEntity = userCardRepository.findByTitle(UserCardType.MODEL_STUDENT.getTitle());
            }
            userEntity.assignUserCard(userCardEntity);

            return ResponseTutorialChoiceDto.fromEntity(userCardEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "지원하지 않는 선택지입니다.");
        }
    }
}