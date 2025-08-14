package org.example.schedule.comments.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.comments.dto.CommentResponse;
import org.example.schedule.comments.dto.CommentSaveRequest;
import org.example.schedule.comments.dto.CommentUpdateRequest;
import org.example.schedule.comments.entity.Comment;
import org.example.schedule.comments.repository.CommentRepository;
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

    // 필요한 리포지토리들을 주입받음
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 저장 로직
    @Transactional
    public CommentResponse save(Long userId, Long scheduleId, CommentSaveRequest request) {

        // 스케줄과 사용자 정보를 DB에서 찾아옴 (없으면 예외 발생)
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule Id " + scheduleId + " not found.")
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User Id " + userId + " not found.")
        );

        // 새로운 댓글 엔티티를 생성하고 저장
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

    // 특정 스케줄의 댓글 목록 조회 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public List<CommentResponse> findScheduleComments(Long scheduleId) {

        // 스케줄 ID로 댓글 목록을 DB에서 찾아옴
        List<Comment> comments = commentRepository.findByScheduleId(scheduleId);

        // 댓글 목록을 CommentResponse DTO 리스트로 변환하여 반환
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

    // ID로 특정 댓글 조회 (읽기 전용 트랜잭션)
    @Transactional(readOnly = true)
    public CommentResponse findCommentById(Long id) {

        // ID로 댓글을 DB에서 찾아옴 (없으면 예외 발생)
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment Id " + id + " not found.")
                );

        // 찾아온 댓글 정보를 DTO로 변환하여 반환
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getSchedule().getId(),
                comment.getComment(),
                comment.getCreatedDate(),
                comment.getModifiedDate()
        );
    }

    // 댓글 수정 로직
    @Transactional
    public CommentResponse updateComment(Long commentId, Long userId, CommentUpdateRequest request) {

        // ID로 댓글을 DB에서 찾아옴 (없으면 예외 발생)
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment Id " + commentId + " not found."));

        // 댓글 작성자와 현재 로그인한 사용자가 일치하는지 확인
        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User Id " + userId + " not found.");
        }

        // 댓글 내용을 업데이트
        comment.updateComment(request.getComment());

        // 수정된 댓글 정보를 DTO로 변환하여 반환
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getSchedule().getId(),
                comment.getComment(),
                comment.getCreatedDate(),
                comment.getModifiedDate());
    }

    // 댓글 삭제 로직
    @Transactional
    public void delete(Long commentId, Long userId) {

        // ID로 댓글을 DB에서 찾아옴 (없으면 예외 발생)
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment Id " + commentId + " not found."));

        // 댓글 작성자와 현재 로그인한 사용자가 일치하는지 확인
        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You can able to delete only own comments.");
        }

        // 댓글을 DB에서 삭제
        commentRepository.delete(comment);
    }
}
