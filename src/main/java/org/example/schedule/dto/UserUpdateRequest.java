package org.example.schedule.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequest {

    private String username;
    private String email;
    private String password;
}
