package org.example.schedule.comments.repository;

import org.example.schedule.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findSchedule(Long scheduleId);
}
