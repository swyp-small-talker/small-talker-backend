package com.swygbr.backend.mypage.service;

import com.swygbr.backend.exception.http_exceptions.UnauthorizedException;
import com.swygbr.backend.mypage.dto.MypageDTO;
import com.swygbr.backend.mypage.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MypageService {

    @Autowired
    private MypageRepository mypageRepository;

    public MypageDTO getMyPageMainInfo(Long id) {
        return mypageRepository.findMyPageInfo(id).orElseThrow(() -> new UnauthorizedException("ERROR OCCURRED"));
    }
}
