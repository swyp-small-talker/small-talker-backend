package com.swygbr.backend.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserInformationDto {
    private String email;
    private String name;
    private String profileImageUrl;
}
