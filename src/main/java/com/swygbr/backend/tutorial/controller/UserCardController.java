package com.swygbr.backend.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swygbr.backend.tutorial.service.UserCardService;

@RestController
@RequestMapping("/user-card")
public class UserCardController {

    @Autowired
    private UserCardService userCardService;

    @PostMapping("/small-talker")
    public ResponseEntity<?> setSmallTalker() {
        if (userCardService.setSmallTalker()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{userCardId}")
    public ResponseEntity<?> getUserCard(@PathVariable(name = "userCardId") Long userCardId) {
        return ResponseEntity.ok(userCardService.findUserCardById(userCardId));
    }
}
