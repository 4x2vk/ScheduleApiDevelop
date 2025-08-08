package org.example.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.UserSaveRequest;
import org.example.schedule.dto.UserSaveResponse;
import org.example.schedule.entity.User;
import org.example.schedule.respository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSaveResponse saveUser(UserSaveRequest request) {
        User user = new User(
                request.getUsername(),
                request.getEmail()
        );
        User savedUser = userRepository.save(user);
        return new UserSaveResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedDate(),
                savedUser.getModifiedDate()
        );
    }
}
