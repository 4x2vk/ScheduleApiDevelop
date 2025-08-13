package org.example.schedule.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.common.filter.LoginFilter;
import org.example.schedule.common.session.SessionConst;
import org.example.schedule.user.dto.*;
import org.example.schedule.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<UserSaveResponse> signUp(@RequestBody UserSaveRequest userSaveRequest) {
        return ResponseEntity.ok(userService.saveUser(userSaveRequest));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserGetAllResponse>> getUsers(@RequestParam(required = false) String username) {
        return ResponseEntity.ok(userService.findUsers(username));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserUpdateResponse> updateUser(@SessionAttribute(name = SessionConst.LOGIN_USER) long userId, @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(userService.update(userId, request));
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.delete(userId);
    }
}
