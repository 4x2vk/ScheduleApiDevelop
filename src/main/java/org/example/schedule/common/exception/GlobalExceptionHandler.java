package org.example.schedule.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

// 전역 예외 처리를 담당하는 클래스임을 선언
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ApplicationException이 발생했을 때 처리하는 메서드
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handleApplicationException(ApplicationException e) {
        // 예외 메시지와 HTTP 상태 코드를 담아 응답
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    // @Valid 검증 실패 시 발생하는 MethodArgumentNotValidException을 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        // 발생한 모든 검증 오류 메시지를 하나의 문자열로 합침
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        // Bad Request 상태 코드와 함께 오류 메시지를 반환
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
