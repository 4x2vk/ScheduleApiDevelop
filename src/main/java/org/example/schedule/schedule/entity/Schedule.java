package org.example.schedule.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule.common.entity.BaseEntity;
import org.example.schedule.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    // 기본 키(PK) 필드 지정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    // User와의 다대일(ManyToOne) 관계 설정
    // user_id 외래 키(FK)로 연결되며, 지연 로딩(Lazy Loading) 사용
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Schedule 엔티티를 생성하는 생성자
    public Schedule(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    // 일정 내용을 수정하는 메서드
    public void updateTitleAuthor(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
