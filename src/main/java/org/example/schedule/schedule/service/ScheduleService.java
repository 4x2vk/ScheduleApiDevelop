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

    // 스케줄 저장 로직
    @Transactional
    public ScheduleSaveResponseDto saveSchedule(Long userId, ScheduleSaveRequestDto requestDto) {
        // 사용자 ID로 사용자 엔티티를 찾아옴 (없으면 예외 발생)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 새로운 스케줄 엔티티 생성
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getDescription(),
                user
        );
        // 스케줄을 데이터베이스에 저장
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

    // 스케줄 목록 조회 로직 (읽기 전용)
    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponse> findSchedules(Long userId) {
        // 모든 스케줄을 DB에서 조회
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleGetAllResponse> dtos = new ArrayList<>();

        if (userId == null) {
            for (Schedule schedule : schedules) {
                dtos.add(new ScheduleGetAllResponse(
                        schedule.getId(),
                        schedule.getUser().getId(),
                        schedule.getTitle(),
                        schedule.getDescription(),
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
                         schedule.getUser().getId(),
                         schedule.getTitle(),
                         schedule.getDescription(),
                         schedule.getCreatedDate(),
                         schedule.getModifiedDate()
                   ));
               }
           }
        return dtos;
    }

    // 특정 스케줄 조회 로직 (읽기 전용)
    @Transactional(readOnly = true)
    public ScheduleGetOneResponse findSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Schedule id " + id + " not found")
        );
        return new ScheduleGetOneResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getCreatedDate(),
                schedule.getModifiedDate()
        );
    }

    // 스케줄 수정 로직
    @Transactional
    public ScheduleUpdateResponse update(Long scheduleId, Long userId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("Schedule id " + scheduleId + " not found")
        );

        if (!userId.equals(schedule.getUser().getId())) {
            throw new IllegalArgumentException("You can change only yours schedule");
        }

        schedule.updateTitleAuthor(
                request.getTitle(),
                request.getDescription()
        );

        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getCreatedDate(),
                schedule.getModifiedDate()
        );
    }

    // 스케줄 삭제 로직
    @Transactional
    public void delete(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("Schedule id " + scheduleId + " not found")
        );

        // 스케줄 작성자와 현재 로그인한 사용자가 일치하는지 확인
        if (!userId.equals(schedule.getUser().getId())) {
            throw new IllegalArgumentException("You can delete only yours schedule");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
