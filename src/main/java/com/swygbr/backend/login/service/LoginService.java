package com.swygbr.backend.login.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.login.dto.KakaoTokenResponse;
import com.swygbr.backend.login.dto.KakaoUserResponse;
import com.swygbr.backend.login.dto.UserDto;
import com.swygbr.backend.login.dto.UserInformationDto;
import com.swygbr.backend.login.property.KakaoProperty;
import com.swygbr.backend.user.domain.RoleType;
import com.swygbr.backend.user.domain.UserEntity;
import com.swygbr.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final KakaoProperty kakaoProperty;

    public UserDto kakaoCallback(String code) {
        KakaoTokenResponse kakaoTokenResponse = this.getToken(code);
        UserInformationDto userInformationDto = this.getKakaoUserInformation(kakaoTokenResponse);
        UserEntity user = userJoin(userInformationDto);

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .profileImgUrl(user.getProfile())
                .build();
    }

    private KakaoTokenResponse getToken(String code) {
        String kakaoUrl = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", this.kakaoProperty.getKakaoClientId());
        params.add("redirect_uri", this.kakaoProperty.getKakaoRedirectionUri());
        params.add("code", code);
        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<>(params, headers);

        RestTemplate rt = new RestTemplate();
        rt.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<KakaoTokenResponse> response = rt.exchange(
                kakaoUrl,
                HttpMethod.POST,
                requestBody,
                KakaoTokenResponse.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "get kakao token error");
        }
        return response.getBody();
    }

    public UserInformationDto getKakaoUserInformation(KakaoTokenResponse KakaoTokenResponse) {
        String kakaoMeUrl = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + KakaoTokenResponse.getAccessToken());
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<KakaoUserResponse> response = rt.exchange(
                kakaoMeUrl,
                HttpMethod.POST,
                body,
                KakaoUserResponse.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "get kakao user inform error");
        }
        KakaoUserResponse kakaoUserResponse = response.getBody();
        UserInformationDto userDto = UserInformationDto.builder()
                .email(kakaoUserResponse.getKakaoUser().getEmail())
                .name(kakaoUserResponse.getKakaoUser().getProfile().getNickname())
                .profileImageUrl(kakaoUserResponse.getKakaoUser().getProfile().getProfileImageUrl())
                .build();

        return userDto;
    }

    public UserEntity userJoin(UserInformationDto userInformationDto) {
        Optional<UserEntity> user = userRepository.findByEmail(userInformationDto.getEmail());
        if (user.isPresent()) {
            return user.get();
        }
        UserEntity newUserEntity = new UserEntity(userInformationDto.getEmail(), userInformationDto.getName(),
                userInformationDto.getProfileImageUrl(), RoleType.ROLE_USER);

        return userRepository.save(newUserEntity);
    }
}