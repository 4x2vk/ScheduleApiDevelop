package org.example.schedule.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.common.session.SessionConst;
import org.example.schedule.schedule.dto.*;
import org.example.schedule.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponseDto> saveSchedule(@SessionAttribute(name = SessionConst.LOGIN_USER) Long userId, @RequestBody ScheduleSaveRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.saveSchedule(userId, requestDto));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetAllResponse>> getSchedules(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(scheduleService.findSchedules(userId));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetOneResponse> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findSchedule(id));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(@SessionAttribute(name = SessionConst.LOGIN_USER) Long userId, @PathVariable Long id, @RequestBody ScheduleUpdateRequest request) {
        return ResponseEntity.ok(scheduleService.update(id, userId, request));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedule(@PathVariable long scheduleId) {
        scheduleService.delete(scheduleId);
    }
}
