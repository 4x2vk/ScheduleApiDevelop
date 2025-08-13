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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public void updateTitleAuthor(String title, User user) {
        this.title = title;
        this.user = user;
    }
}
