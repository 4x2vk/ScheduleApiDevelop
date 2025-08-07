package org.example.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleSaveRequestDto;
import org.example.schedule.dto.ScheduleSaveResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.respository.ScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getAuthor(),
                requestDto.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleSaveResponseDto(
                savedSchedule.getUserId(),
                savedSchedule.getTitle(),
                savedSchedule.getDescription(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedDate(),
                savedSchedule.getModifiedDate()
                );
    }
}
