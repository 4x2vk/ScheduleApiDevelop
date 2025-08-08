package org.example.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.UserSaveRequest;
import org.example.schedule.dto.UserSaveResponse;
import org.example.schedule.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserSaveResponse> saveUser(@RequestBody UserSaveRequest userSaveRequest) {
        return ResponseEntity.ok(userService.saveUser(userSaveRequest));
    }
}
