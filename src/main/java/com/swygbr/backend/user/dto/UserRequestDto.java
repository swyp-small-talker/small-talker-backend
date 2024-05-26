package com.swygbr.backend.user.dto;

public record UserRequestDto(String name, String base64Profile) {
    public UserRequestDto(String name, String base64Profile) {
        this.name = name;
        this.base64Profile = base64Profile.replaceFirst("^data:image/(png|jpg);base64,", "");
    }
}