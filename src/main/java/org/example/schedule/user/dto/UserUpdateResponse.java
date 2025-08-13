package org.example.schedule.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserUpdateResponse {

    private final String username;
    private final String email;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public UserUpdateResponse(String username, String email, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.username = username;
        this.email = email;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
