package org.example.schedule.dto;

import lombok.Getter;

@Getter
public class UserUpdateResponse {

    private final String username;
    private final String email;

    public UserUpdateResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
