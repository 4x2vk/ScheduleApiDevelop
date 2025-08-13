package org.example.schedule.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedule.common.filter.LoginFilter;
import org.example.schedule.common.session.SessionConst;
import org.example.schedule.user.dto.*;
import org.example.schedule.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<UserSaveResponse> signUp(@Valid @RequestBody UserSaveRequest userSaveRequest) {
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

    @DeleteMapping("/users/me")
    public ResponseEntity<Void> deleteUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        userService.delete(userId);
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
