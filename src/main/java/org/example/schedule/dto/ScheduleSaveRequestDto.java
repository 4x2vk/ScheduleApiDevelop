package org.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    private String title;
    private String description;
    private Long userId;
}
