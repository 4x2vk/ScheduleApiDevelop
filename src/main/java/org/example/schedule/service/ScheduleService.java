package org.example.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.*;
import org.example.schedule.entity.Schedule;
import org.example.schedule.entity.User;
import org.example.schedule.respository.ScheduleRepository;
import org.example.schedule.respository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getDescription(),
                user
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleSaveResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getDescription(),
                savedSchedule.getUser().getUsername(),
                savedSchedule.getCreatedDate(),
                savedSchedule.getModifiedDate()
                );
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponse> findSchedules(Long userId) {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleGetAllResponse> dtos = new ArrayList<>();

        if (userId == null) {
            for (Schedule schedule : schedules) {
                dtos.add(new ScheduleGetAllResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getDescription(),
                        schedule.getUser().getUsername(),
                        schedule.getCreatedDate(),
                        schedule.getModifiedDate()
                ));
            }
            return dtos;
        }

        for (Schedule schedule : schedules) {
               if (userId.equals(schedule.getUser().getId())) {
                   dtos.add(new ScheduleGetAllResponse(
                         schedule.getId(),
                         schedule.getTitle(),
                         schedule.getDescription(),
                         schedule.getUser().getUsername(),
                         schedule.getCreatedDate(),
                         schedule.getModifiedDate()
                   ));
               }
           }
        return dtos;
    }

    @Transactional(readOnly = true)
    public ScheduleGetOneResponse findSchedule(long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("Schedule id " + scheduleId + " not found")
        );
        return new ScheduleGetOneResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getUser().getUsername(),
                schedule.getCreatedDate(),
                schedule.getModifiedDate()
        );
    }

    @Transactional
    public ScheduleUpdateResponse update(long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("Schedule id " + scheduleId + " not found")
        );

        User user = userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        schedule.updateTitleAuthor(
                request.getTitle(),
                user
        );

        return new ScheduleUpdateResponse(
                schedule.getTitle(),
                schedule.getUser().getId()
        );
    }

    @Transactional
    public void delete(long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("Schedule id " + scheduleId + " not found")
        );
        scheduleRepository.deleteById(scheduleId);
    }
}
