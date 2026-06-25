package com.gokul.busbooking.Security;

import com.gokul.busbooking.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler successHandler;
    private final JwtFilter jwtFilter;

    public SecurityConfig(
            OAuth2LoginSuccessHandler successHandler,
            JwtFilter jwtFilter) {

        this.successHandler = successHandler;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Public Pages
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/admin.html",
                                "/user.html",
                                "/oauth2/**",
                                "/login/**",
                                "/style.css",
                                "/test/public"
                        ).permitAll()

                        // USER + ADMIN Search
                        .requestMatchers(
                                "/bus/search"
                        ).hasAnyRole("USER", "ADMIN")

                        // USER + ADMIN View All Buses
                        .requestMatchers(
                                HttpMethod.GET,
                                "/bus",
                                "/bus/*"
                        ).hasAnyRole("USER", "ADMIN")

                        // USER + ADMIN Booking
                        .requestMatchers(
                                "/booking/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // ADMIN Add Bus
                        .requestMatchers(
                                HttpMethod.POST,
                                "/bus"
                        ).hasRole("ADMIN")

                        // ADMIN Update Bus
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/bus/**"
                        ).hasRole("ADMIN")

                        // ADMIN Delete Bus
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/bus/**"
                        ).hasRole("ADMIN")

                        // Admin APIs
                        .requestMatchers(
                                "/admin/**"
                        ).hasRole("ADMIN")

                        // User APIs
                        .requestMatchers(
                                "/user/**"
                        ).hasAnyRole("USER", "ADMIN")

                        .anyRequest()
                        .authenticated()
                )

                .oauth2Login(oauth ->
                        oauth.successHandler(successHandler)
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}