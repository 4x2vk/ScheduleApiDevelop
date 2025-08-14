package org.example.schedule.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

// 로그를 위한 Slf4j 어노테이션
@Slf4j
public class LoginFilter implements Filter {
    // 로그인 없이 접근 가능한 URL 목록
    private static final String[] WHITE_LIST = {"/", "/users/signup", "/login", "/logout"};

    // 필터의 핵심 로직
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest  = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        // 화이트리스트에 포함되지 않은 경우, 로그인 여부 확인
        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("LOGIN_USER") == null) {
                // 401 Unauthorized 에러 반환 후 필터 체인 종료
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please login first");
                return;
            }
        }
        // 요청이 화이트리스트에 있거나 로그인 상태인 경우 다음 필터로 진행
        chain.doFilter(request, response);
    }
    // 요청 URI가 화이트리스트에 포함되는지 확인하는 보조 메서드
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
