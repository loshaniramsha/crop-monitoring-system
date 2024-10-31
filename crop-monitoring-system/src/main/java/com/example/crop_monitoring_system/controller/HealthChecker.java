package com.example.crop_monitoring_system.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthChecker {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String health() {
        return "Crop Monitoring Project is working";
    }
}
