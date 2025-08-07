package org.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateResponse {

    private final String title;
    private final String author;

    public ScheduleUpdateResponse(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
