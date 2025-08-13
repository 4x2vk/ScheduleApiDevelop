package org.example.schedule.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    @NotBlank(message = "title is required")
    @Size(max = 5, message = "at least 5 symbols")
    private String title;

    @NotBlank(message = "description is required")
    @Size(max = 255, message = "max 255 symbols")
    private String description;
}
