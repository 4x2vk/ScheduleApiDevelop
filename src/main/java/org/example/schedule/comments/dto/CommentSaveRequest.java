package org.example.schedule.comments.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentSaveRequest {

    @NotBlank(message = "comment is required")
    private String comment;
}
