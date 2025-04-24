package com.giftsubscription.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public Map<String, String> hello() {
        return Map.of("message", "Привет от Spring Boot!");
    }
}
