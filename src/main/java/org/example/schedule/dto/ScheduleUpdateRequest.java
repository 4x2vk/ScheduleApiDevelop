package org.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {

    private String title;
    private Long userId;
    private String password;
}
