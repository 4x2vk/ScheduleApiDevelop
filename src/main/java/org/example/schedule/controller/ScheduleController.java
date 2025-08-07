package org.example.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleSaveRequestDto;
import org.example.schedule.dto.ScheduleSaveResponseDto;
import org.example.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponseDto> save(@RequestBody ScheduleSaveRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.saveSchedule(requestDto));
    }
}
