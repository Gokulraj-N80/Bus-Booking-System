package com.gokul.busbooking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/public")
    public String publicApi() {
        return "Public API working";
    }

    @GetMapping("/user")
    public String userApi() {
        return "USER API working";
    }

    @GetMapping("/admin")
    public String adminApi() {
        return "ADMIN API working";
    }
}