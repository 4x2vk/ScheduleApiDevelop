package org.example.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.*;
import org.example.schedule.entity.User;
import org.example.schedule.respository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<UserGetAllResponse> findUsers(String username) {
        List<User> users = userRepository.findAll();
        List<UserGetAllResponse> dtos = new ArrayList<>();

        if (username == null) {
            for (User user : users) {
                dtos.add(new UserGetAllResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getCreatedDate(),
                        user.getModifiedDate()
                ));
            }
            return dtos;
        }

        for (User user : users) {
            if (username.equals(user.getUsername())) {
                dtos.add(new UserGetAllResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getCreatedDate(),
                        user.getModifiedDate()
                ));
            }
        }
        return dtos;
    }

    @Transactional
    public UserUpdateResponse update(long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User with id " + userId + " not found")
        );

        user.updateUsernameEmail(request.getUsername(), request.getEmail());
        return new UserUpdateResponse(
                user.getUsername(),
                user.getEmail()
        );
    }
}
