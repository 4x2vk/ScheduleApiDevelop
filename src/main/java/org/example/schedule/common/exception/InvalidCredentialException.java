package org.example.schedule.common.exception;

import org.springframework.http.HttpStatus;

// ApplicationException을 상속받아 InvalidCredentialException을 정의
public class InvalidCredentialException extends ApplicationException {
    // 메시지를 받아 HTTP 상태 코드 401(UNAUTHORIZED)를 기본값으로 설정
    public InvalidCredentialException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
