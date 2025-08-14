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

    // 기본 키(PK) 필드, 자동 생성 전략 사용
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 댓글 내용 필드
    private String comment;

    // Schedule과의 다대일(ManyToOne) 관계 설정. Eager Loading 대신 Lazy Loading 사용
    // schedule_id 외래 키(FK)로 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    // User와의 다대일(ManyToOne) 관계 설정. Eager Loading 대신 Lazy Loading 사용
    // user_id 외래 키(FK)로 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 댓글 생성자
    public Comment(String comment, Schedule schedule, User user) {
        this.comment = comment;
        this.schedule = schedule;
        this.user = user;
    }

    // 댓글 내용 수정 메서드
    public void updateComment(String comment) {
        this.comment = comment;
    }
}
