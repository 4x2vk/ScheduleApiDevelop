package org.example.schedule.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedule.user.dto.LoginRequest;
import org.example.schedule.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    // 로그인 요청 처리
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpRequest) {

        Long userId = userService.handleLogin(request);

        // 로그인 성공 시 세션을 생성하거나 가져옴
        HttpSession session = httpRequest.getSession();
        // 세션에 로그인한 사용자의 ID를 저장
        session.setAttribute("LOGIN_USER", userId);

        return ResponseEntity.ok("success");
    }

    // 로그아웃 요청 처리
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // 현재 세션을 가져옴 (세션이 없으면 null 반환)
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션에 로그인 정보가 있으면 세션을 무효화
            session.invalidate();
        }
        return ResponseEntity.ok().body("logout success");
    }
}
