package org.example.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.schedule.dto.*;
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

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserUpdateResponse> updateUser(@PathVariable long userId, @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(userService.update(userId, request));
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.delete(userId);
    }
}
