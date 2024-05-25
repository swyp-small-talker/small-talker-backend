package com.swygbr.backend.mypage.controller;

import com.swygbr.backend.mypage.service.MypageService;
import com.swygbr.backend.mypage.vo.MypageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @GetMapping
    public ResponseEntity<MypageVo> getMypage(@RequestParam(required = false) Long userId) {
        MypageVo mypageVo = mypageService.getMyPageMainInfo(userId);
        if (mypageVo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mypageVo);
    }
}
