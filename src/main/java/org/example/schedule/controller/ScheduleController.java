package org.example.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.*;
import org.example.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponseDto> saveSchedule(@RequestBody ScheduleSaveRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.saveSchedule(requestDto));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetAllResponse>> getSchedules(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(scheduleService.findSchedules(userId));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetOneResponse> getSchedule(@PathVariable long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(@PathVariable long scheduleId, @RequestBody ScheduleUpdateRequest request) {
        return ResponseEntity.ok(scheduleService.update(scheduleId, request));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedule(@PathVariable long scheduleId, @RequestParam String password) {
        scheduleService.delete(scheduleId, password);
    }
}
