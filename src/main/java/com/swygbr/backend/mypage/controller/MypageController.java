package com.swygbr.backend.mypage.controller;

import com.swygbr.backend.mypage.dto.MypageDTO;
import com.swygbr.backend.mypage.service.MypageService;
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
    private MypageService mypageService;

    // 마이페이지 정보 조회
    @GetMapping
    public ResponseEntity<?> getMyPageInfo(@SessionAttribute(name = "id", required = false) Long id) {
        return ResponseEntity.ok(mypageService.getMyPageMainInfo(id));
    }

    //획득한 스킬 목록
    public ResponseEntity<?> getMySkills() {
        return ResponseEntity.ok("");
    }


}
