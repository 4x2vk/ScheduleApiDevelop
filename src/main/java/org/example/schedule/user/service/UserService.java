package org.example.schedule.user.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule.common.exception.InvalidCredentialException;
import org.example.schedule.user.dto.*;
import org.example.schedule.user.entity.User;
import org.example.schedule.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 로직
    @Transactional
    public UserSaveResponse saveUser(UserSaveRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
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
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedDate(),
                user.getModifiedDate()
        );
    }

    @Transactional
    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public Long handleLogin(LoginRequest request) {

        if (request.getPassword() == null) {
            throw new InvalidCredentialException("Password must be provided");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialException("Unknown email"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("Password mismatch");
        }
        return user.getId();
    }
}
