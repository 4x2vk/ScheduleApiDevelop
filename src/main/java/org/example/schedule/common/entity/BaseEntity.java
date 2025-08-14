package org.example.schedule.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
// JPA의 엔티티 매핑 정보가 이 클래스를 상속받는 자식 클래스에 포함되도록 지정
@MappedSuperclass
// JPA Auditing 기능을 사용하기 위해 리스너를 지정
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // 엔티티가 생성될 때 시간이 자동으로 기록됨
    @CreatedDate
    // 이 필드는 업데이트되지 않도록 설정
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    // 엔티티가 수정될 때 시간이 자동으로 기록됨
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;
}
