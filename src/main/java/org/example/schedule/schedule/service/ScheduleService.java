package org.example.schedule.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.schedule.dto.*;
import org.example.schedule.schedule.entity.Schedule;
import org.example.schedule.user.entity.User;
import org.example.schedule.schedule.repository.ScheduleRepository;
import org.example.schedule.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(Long userId, ScheduleSaveRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getDescription(),
                user
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleSaveResponseDto(
                savedSchedule.getId(),
                user.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getDescription(),
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
    public ScheduleGetOneResponse findSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Schedule id " + id + " not found")
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
    public ScheduleUpdateResponse update(Long scheduleId, Long userId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("Schedule id " + scheduleId + " not found")
        );

        if (userId.equals(schedule.getUser().getId())) {
            throw new IllegalArgumentException("You can change only yours schedule");
        }

        schedule.updateTitleAuthor(
                request.getTitle(),
                request.getDescription()
        );

        return new ScheduleUpdateResponse(
                schedule.getTitle(),
                schedule.getUser().getId()
        );
    }

    @Transactional
    public void delete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("Schedule id " + scheduleId + " not found")
        );
        scheduleRepository.deleteById(scheduleId);
    }
}
