package com.swygbr.backend.user.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.user.domain.UserEntity;

public record UserResponseDto(Long userId, String email, String name, String profile) {
    public static UserResponseDto fromEntity(UserEntity entity) {
        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.");
        }

        UserResponseDto dto = new UserResponseDto(entity.getId(), entity.getEmail(), entity.getName(),
                entity.getProfile());
        return dto;
    }
}
