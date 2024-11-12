package com.example.crop_monitoring_system.entity;

public enum States {
    AVAILABLE,
    NOT_AVAILABLE;

    public String toUpperCase() {
        return name().toUpperCase();
    }
}
