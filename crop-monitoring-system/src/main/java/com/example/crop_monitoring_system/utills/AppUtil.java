package com.example.crop_monitoring_system.utills;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String convertImageToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);

    }

}
