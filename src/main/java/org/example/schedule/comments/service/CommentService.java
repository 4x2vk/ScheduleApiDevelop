package org.example.schedule.comments.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.comments.dto.CommentResponse;
import org.example.schedule.comments.dto.CommentSaveRequest;
import org.example.schedule.comments.dto.CommentUpdateRequest;
import org.example.schedule.comments.entity.Comment;
import org.example.schedule.comments.repository.CommentRepository;
import org.example.schedule.schedule.dto.ScheduleGetOneResponse;
import org.example.schedule.schedule.entity.Schedule;
import org.example.schedule.schedule.repository.ScheduleRepository;
import org.example.schedule.user.entity.User;
import org.example.schedule.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponse save(Long userId, Long scheduleId, CommentSaveRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule Id " + scheduleId + " not found.")
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User Id " + userId + " not found.")
        );
        Comment comment = new Comment(request.getComment(), schedule, user);
        commentRepository.save(comment);
        return new CommentResponse(
                comment.getId(),
                user.getId(),
                schedule.getId(),
                comment.getComment(),
                comment.getCreatedDate(),
                comment.getModifiedDate()
        );
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findScheduleComments(Long scheduleId) {
        List<Comment> comments = commentRepository.findSchedule(scheduleId);
        return comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getUser().getId(),
                        comment.getSchedule().getId(),
                        comment.getComment(),
                        comment.getCreatedDate(),
                        comment.getModifiedDate()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentResponse findCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment Id " + id + " not found.")
                );
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getSchedule().getId(),
                comment.getComment(),
                comment.getCreatedDate(),
                comment.getModifiedDate()
        );
    }

    @Transactional
    public CommentResponse updateComment(Long commentId, Long userId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment Id " + commentId + " not found."));
        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User Id " + userId + " not found.");
        }
        comment.updateComment(request.getComment());
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getSchedule().getId(),
                comment.getComment(),
                comment.getCreatedDate(),
                comment.getModifiedDate());
    }

    @Transactional
    public void delete(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment Id " + commentId + " not found."));
        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You can able to delete only own comments.");
        }
        commentRepository.delete(comment);
    }
}
