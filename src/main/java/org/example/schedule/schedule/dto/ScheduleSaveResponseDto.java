package org.example.schedule.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveResponseDto {

    private final Long id;
    private final Long userId;
    private final String title;
    private final String description;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public ScheduleSaveResponseDto(Long id , Long userId, String title, String description, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
