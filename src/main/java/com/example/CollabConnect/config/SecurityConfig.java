package com.example.CollabConnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/videos/upload").authenticated()
//                .requestMatchers("/api/videos/stream/**", "/api/videos/metadata/**").permitAll()
//                .and()
//                .httpBasic(); // Basic authentication
//        return http.build();
        http.authorizeHttpRequests((requests) -> {
            (requests
                    .requestMatchers("/api/videos/stream/**", "/api/videos/metadata/**","/api/videos/upload").permitAll()
                    .anyRequest()).authenticated();
        });
        http.httpBasic(Customizer.withDefaults());
        http.headers(headers->headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()));
        http.csrf(csrf->csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}