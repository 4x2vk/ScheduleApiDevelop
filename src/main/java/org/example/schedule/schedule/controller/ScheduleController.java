package org.example.schedule.schedule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedule.common.session.SessionConst;
import org.example.schedule.schedule.dto.*;
import org.example.schedule.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// RESTful API 컨트롤러임을 선언
@RestController
// final 필드에 대한 생성자를 자동 생성
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 새 스케줄 생성
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponseDto> saveSchedule(HttpServletRequest request, @RequestBody ScheduleSaveRequestDto requestDto) {

        // 세션에서 로그인 사용자 ID를 가져옴
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        // 서비스 메서드를 호출하여 스케줄을 저장하고, 201 Created 상태 코드와 함께 응답
        return ResponseEntity.ok(scheduleService.saveSchedule(userId, requestDto));
    }

    // 모든 스케줄 조회 (선택적으로 사용자 ID로 필터링)
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetAllResponse>> getSchedules(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(scheduleService.findSchedules(userId));
    }

    // 특정 ID의 스케줄 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleGetOneResponse> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findSchedule(id));
    }

    // 특정 ID의 스케줄 수정
    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(@Valid @SessionAttribute(name = SessionConst.LOGIN_USER) Long userId, @PathVariable Long id, @RequestBody ScheduleUpdateRequest request) {
        return ResponseEntity.ok(scheduleService.update(id, userId, request));
    }

    // 특정 ID의 스케줄 삭제
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@SessionAttribute(name = SessionConst.LOGIN_USER) Long userId, @PathVariable Long id) {
        scheduleService.delete(id, userId);
        return ResponseEntity.ok().build();
    }
}
