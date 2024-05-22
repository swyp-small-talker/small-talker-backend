package com.swygbr.backend.mypage.controller;

import com.swygbr.backend.practice.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

@RestController
@RequestMapping("/mypage")
public class MypageController {
    @Autowired
    private PracticeService practiceService;

    // 마이페이지 정보 조회
    @GetMapping
    public ResponseEntity<?> getMyPageInfo(@SessionAttribute(name = "userId", required = false) String userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Map<String, Object> myPageInfo = practiceService.getMyPageInfo(userId);
        return ResponseEntity.ok(myPageInfo);
    }
}
