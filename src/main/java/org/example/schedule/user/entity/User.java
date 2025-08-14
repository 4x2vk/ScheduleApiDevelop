package org.example.schedule.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule.common.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    // 기본 키(PK) 필드 지정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 이름 필드. 중복을 허용하지 않도록 설정
    @Column(nullable = false, unique = true)
    private String username;

    // 이메일 필드. 중복을 허용하지 않도록 설정
    @Column(nullable = false, unique = true)
    private String email;

    // 비밀번호 필드. BCrypt로 해시된 값이 저장됨
    private String password;

    // User 엔티티를 생성하는 생성자
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // 사용자 정보를 업데이트하는 메서드
    public void updateUsernameEmail(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
