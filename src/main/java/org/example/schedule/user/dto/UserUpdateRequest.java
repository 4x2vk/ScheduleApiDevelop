package org.example.schedule.user.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequest {

    private String username;
    private String email;
    private String password;
}
