package org.example.schedule.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateResponse {

    private final String title;
    private final Long userId;

    public ScheduleUpdateResponse(String title, Long userId) {
        this.title = title;
        this.userId = userId;
    }
}
