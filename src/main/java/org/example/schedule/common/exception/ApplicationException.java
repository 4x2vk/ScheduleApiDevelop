package org.example.schedule.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
// RuntimeException을 상속하여 컴파일러가 체크하지 않는 언체크드 예외로 정의
public class ApplicationException extends RuntimeException {

    // 예외와 함께 반환할 HTTP 상태 코드
    private final HttpStatus httpStatus;

    // 예외 메시지와 HTTP 상태 코드를 받아 객체를 생성하는 생성자
    public ApplicationException(String message, HttpStatus httpStatus) {
        super(message); // RuntimeException의 생성자 호출
        this.httpStatus = httpStatus;
    }
}
