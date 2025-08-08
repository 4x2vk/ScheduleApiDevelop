package org.example.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule.dto.UserGetAllResponse;
import org.example.schedule.dto.UserSaveRequest;
import org.example.schedule.dto.UserSaveResponse;
import org.example.schedule.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserSaveResponse> saveUser(@RequestBody UserSaveRequest userSaveRequest) {
        return ResponseEntity.ok(userService.saveUser(userSaveRequest));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserGetAllResponse>> getUsers(@RequestParam(required = false) String username) {
        return ResponseEntity.ok(userService.findUsers(username));
    }
}
