package org.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetOneResponse {

    private final Long scheduleId;
    private final String title;
    private final String description;
    private final String author;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public ScheduleGetOneResponse(Long scheduleId, String title, String description, String author, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.description = description;
        this.author = author;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
