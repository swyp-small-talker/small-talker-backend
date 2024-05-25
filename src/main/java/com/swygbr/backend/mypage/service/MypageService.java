package com.swygbr.backend.mypage.service;

import com.swygbr.backend.mypage.mapper.MypageMapper;
import com.swygbr.backend.mypage.vo.MypageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final MypageMapper mypageMapper; // MypageMapper 주입

    public MypageVo getMyPageMainInfo(Long id) {
        if (id == null) {
            id = 1L; // 테스트용으로 ID가 없을 때 1로 고정
        }
        return mypageMapper.findMyPageInfo(id);
    }
}
