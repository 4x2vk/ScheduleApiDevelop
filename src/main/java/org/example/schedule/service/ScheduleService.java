package org.example.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.ScheduleGetAllResponse;
import org.example.schedule.dto.ScheduleGetOneResponse;
import org.example.schedule.dto.ScheduleSaveRequestDto;
import org.example.schedule.dto.ScheduleSaveResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.respository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponse> findSchedules(String author) {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleGetAllResponse> dtos = new ArrayList<>();

        if (author == null) {
            for (Schedule schedule : schedules) {
                dtos.add(new ScheduleGetAllResponse(
                        schedule.getUserId(),
                        schedule.getTitle(),
                        schedule.getDescription(),
                        schedule.getAuthor(),
                        schedule.getCreatedDate(),
                        schedule.getModifiedDate()
                ));
            }
            return dtos;
        }

        for (Schedule schedule : schedules) {
               if (author.equals(schedule.getAuthor())) {
                   dtos.add(new ScheduleGetAllResponse(
                         schedule.getUserId(),
                         schedule.getTitle(),
                         schedule.getDescription(),
                         schedule.getAuthor(),
                         schedule.getCreatedDate(),
                         schedule.getModifiedDate()
                   ));
               }
           }
        return dtos;
    }
}
