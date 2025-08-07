package org.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveResponseDto {

    private final Long id;
    private final String title;
    private final String description;
    private final String author;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public ScheduleSaveResponseDto(Long id, String title, String description, String author, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
