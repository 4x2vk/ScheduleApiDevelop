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

    // CommentService를 주입받아 비즈니스 로직을 처리
    private final CommentService commentService;

    // 특정 스케줄에 새 댓글을 추가
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponse> save(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long scheduleId,
            @RequestBody CommentSaveRequest request
    ) {
        // 서비스 메서드를 호출하여 댓글을 저장하고, 성공 응답을 반환
        return ResponseEntity.ok(commentService.save(userId, scheduleId, request));
    }

    // 특정 스케줄의 모든 댓글을 조회
    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentBySchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.findScheduleComments(scheduleId));
    }

    // ID로 특정 댓글을 조회
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findCommentById(id));
    }

    // ID에 해당하는 댓글을 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(@SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long id,
            @RequestBody CommentUpdateRequest request
    ) {
        return ResponseEntity.ok(commentService.updateComment(id, userId, request));
    }

    // ID에 해당하는 댓글을 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@SessionAttribute(name = "LOGIN_USER") Long userId, @PathVariable Long id) {
        commentService.delete(id, userId);
        return ResponseEntity.ok().build();
    }
}
