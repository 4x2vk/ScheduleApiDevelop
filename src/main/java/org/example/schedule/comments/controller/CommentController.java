package org.example.schedule.comments.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.comments.dto.CommentResponse;
import org.example.schedule.comments.dto.CommentSaveRequest;
import org.example.schedule.comments.dto.CommentUpdateRequest;
import org.example.schedule.comments.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponse> save(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long scheduleId,
            @RequestBody CommentSaveRequest request
    ) {
        return ResponseEntity.ok(commentService.save(userId, scheduleId, request));
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentBySchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.findScheduleComments(scheduleId));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findCommentById(id));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(@SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long id,
            @RequestBody CommentUpdateRequest request
    ) {
        return ResponseEntity.ok(commentService.updateComment(id, userId, request));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@SessionAttribute(name = "LOGIN_USER") Long userId, @PathVariable Long id) {
        commentService.delete(id, userId);
        return ResponseEntity.ok().build();
    }
}
