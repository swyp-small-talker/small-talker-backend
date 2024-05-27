package com.swygbr.backend.tutorial.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.tutorial.domain.UserCardEntity;
import com.swygbr.backend.tutorial.dto.UserCardDto;
import com.swygbr.backend.tutorial.repository.UserCardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCardService {
    private final UserCardRepository userCardRepository;

    public UserCardDto findUserCardById(Long userCardId) {
        UserCardEntity entity = userCardRepository.findById(userCardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원카드를 찾을 수 없습니다."));

        return UserCardDto.fromEntity(entity);
    }
}