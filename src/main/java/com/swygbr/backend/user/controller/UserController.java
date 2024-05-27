package com.swygbr.backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swygbr.backend.user.dto.UserRequestDto;
import com.swygbr.backend.user.dto.UserResponseDto;
import com.swygbr.backend.user.dto.UserSkillResponseDto;
import com.swygbr.backend.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<EntityModel<UserResponseDto>> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @GetMapping("/{userId}/skill")
    public ResponseEntity<CollectionModel<UserSkillResponseDto>> getUserSkill(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUserSkill(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> putUserById(@PathVariable Long userId, @RequestBody UserRequestDto requestDto) {
        userService.updateUserById(userId, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
