package org.example.schedule.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 스프링 설정 클래스임을 나타냄
@Configuration
public class SecurityConfig {

    // BCryptPasswordEncoder를 빈으로 등록하여 주입받아 사용 가능하게 함
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable) // Postman 테스트에 대한 CSRF 비활성화
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/users/signup", "/login", "/users/logout").permitAll() // 등록 허용
//                        .requestMatchers("/schedules").authenticated()
//                        .anyRequest().authenticated() // 나머지는 승인이 필요합니다
//                );
//        return http.build();
//    }

}
