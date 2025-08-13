package org.example.schedule.comments.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private final Long id;
    private final Long userId;
    private final Long scheduleId;
    private final String comment;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public CommentResponse(Long id, Long userId, Long scheduleId, String comment, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.comment = comment;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
