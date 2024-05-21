package com.swygbr.backend.tutorial.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.tutorial.domain.UserCardEntity;
import com.swygbr.backend.tutorial.dto.UserCardDto;
import com.swygbr.backend.tutorial.enums.UserCardType;
import com.swygbr.backend.tutorial.repository.UserCardRepository;
import com.swygbr.backend.user.domain.UserEntity;
import com.swygbr.backend.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCardService {
    private final UserRepository userRepository;
    private final UserCardRepository userCardRepository;

    public boolean setSmallTalker() {
        UserEntity userEntity = getDefaultUser();
        if (userEntity.completeTalk()) {
            UserCardEntity entity = userCardRepository.findByTitle(UserCardType.SMALL_TALKER.getTitle());
            userEntity.assignUserCard(entity);
            return true;
        }
        return false;
    }

    public UserCardDto findUserCardById(Long userCardId) {
        UserCardEntity entity = userCardRepository.findById(userCardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원카드를 찾을 수 없습니다."));

        return UserCardDto.fromEntity(entity);
    }

    private UserEntity getDefaultUser() {
        return userRepository.findById(1L)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "기본 유저를 찾을 수 없습니다."));
    }
}