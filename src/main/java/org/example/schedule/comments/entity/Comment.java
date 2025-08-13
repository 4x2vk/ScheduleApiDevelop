package org.example.schedule.comments.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule.common.entity.BaseEntity;
import org.example.schedule.schedule.entity.Schedule;
import org.example.schedule.user.entity.User;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(String comment, Schedule schedule, User user) {
        this.comment = comment;
        this.schedule = schedule;
        this.user = user;
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }
}
