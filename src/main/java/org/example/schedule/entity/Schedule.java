package org.example.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String password;

    public Schedule(String title, String description, User user, String password) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.password = password;
    }

    public void updateTitleAuthor(String title, User user) {
        this.title = title;
        this.user = user;
    }
}
