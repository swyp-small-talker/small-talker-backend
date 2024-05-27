package com.swygbr.backend.user.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.practice.domain.PracticeSkill;
import com.swygbr.backend.practice.repository.PracticeSkillRepository;
import com.swygbr.backend.tutorial.controller.UserCardController;
import com.swygbr.backend.user.controller.UserController;
import com.swygbr.backend.user.domain.UserEntity;
import com.swygbr.backend.user.dto.UserRequestDto;
import com.swygbr.backend.user.dto.UserResponseDto;
import com.swygbr.backend.user.dto.UserSkillResponseDto;
import com.swygbr.backend.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PracticeSkillRepository skillRepository;
    private final ImgurService imgurService;

    public EntityModel<UserResponseDto> findUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));
        UserResponseDto dto = UserResponseDto.fromEntity(userEntity);

        EntityModel<UserResponseDto> model = EntityModel.of(dto);
        if (userEntity.getUserCard() != null) {
            Link userCardLink = linkTo(methodOn(UserCardController.class).getUserCard(userEntity.getUserCard().getId()))
                    .withRel("userCard");
            model.add(userCardLink);
        }
        Link skillLink = linkTo(methodOn(UserController.class).getUserSkill(userId))
                .withRel("skill");
        model.add(skillLink);

        return model;
    }

    public CollectionModel<UserSkillResponseDto> findUserSkill(Long userId) {
        List<PracticeSkill> skillList = skillRepository.findAllSkillsByUserId(userId);
        List<UserSkillResponseDto> list = skillList.stream().map(UserSkillResponseDto::fromEntity).toList();
        CollectionModel<UserSkillResponseDto> model = CollectionModel.of(list);
        Link userLink = linkTo(methodOn(UserController.class).getUserById(userId))
                .withRel("user");
        model.add(userLink);
        return model;
    }

    public void updateUserById(Long userId, UserRequestDto requestDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));

        String profile = userEntity.getProfile();
        try {
            profile = imgurService.uploadImage(requestDto.base64Profile());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), e.getCause());
        }

        userEntity.updateName(requestDto.name());
        userEntity.updateProfile(profile);
        userRepository.save(userEntity);
    }

    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));
        userRepository.delete(userEntity);
    }

}
