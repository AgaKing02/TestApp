package com.example.onlinevote.dto;

import com.example.onlinevote.models.User;

public class ResultDto {
    private final User user;
    private final Boolean isTrue;

    public ResultDto(User user, Boolean isTrue) {
        this.user = user;
        this.isTrue = isTrue;
    }

    public User getUser() {
        return user;
    }

    public Boolean getTrue() {
        return isTrue;
    }
}
