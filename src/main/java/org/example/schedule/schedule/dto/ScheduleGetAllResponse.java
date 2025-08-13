package org.example.schedule.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetAllResponse {

    private final Long userId;
    private final String title;
    private final String description;
    private final String author;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public ScheduleGetAllResponse(Long userId, String title, String description, String author, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.author = author;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
