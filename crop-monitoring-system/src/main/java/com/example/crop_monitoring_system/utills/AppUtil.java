package com.example.crop_monitoring_system.utills;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String convertImageToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);

    }

    public static String convertStringToPoint(String fieldLocation) {
        String[] point = fieldLocation.split(",");

        if (point.length < 2) {
            throw new IllegalArgumentException("Invalid field location format. Expected format: 'latitude,longitude'");
        }

        return point[0].trim() + " " + point[1].trim();
    }
}
