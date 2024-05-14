package com.swygbr.backend.tutorial.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.tutorial.domain.TutorialEntity;
import com.swygbr.backend.tutorial.dto.TutorialDto;
import com.swygbr.backend.tutorial.dto.TutorialStatusDto;
import com.swygbr.backend.tutorial.repository.TutorialRepository;
import com.swygbr.backend.user.domain.UserEntity;
import com.swygbr.backend.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class TutorialService {
    private final TutorialRepository tutorialRepository;
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

    private UserEntity getDefaultUser() {
        return userRepository.findById(1L)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "기본 유저를 찾을 수 없습니다."));
    }
}
