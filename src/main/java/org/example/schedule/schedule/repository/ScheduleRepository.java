package org.example.schedule.schedule.repository;

import org.example.schedule.comments.entity.Comment;
import org.example.schedule.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
