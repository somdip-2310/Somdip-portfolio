package com.somdiproy.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Allow all requests for local testing
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()
            )
            
            // Disable CSRF for local testing
            .csrf(csrf -> csrf.disable())
            
            // Basic security headers
            .headers(headers -> headers
                .frameOptions().sameOrigin()  // More permissive for local
                .contentTypeOptions().and()
            );

        return http.build();
    }
}