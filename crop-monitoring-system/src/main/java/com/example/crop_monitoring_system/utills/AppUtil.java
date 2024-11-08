package com.example.crop_monitoring_system.utills;

import java.awt.*;
import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String convertImageToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);

    }

    public static Point generateFieldlocation() {
        return UUID.randomUUID().toString().substring(0, 3).getBytes().length > 0 ?
                new Point(UUID.randomUUID().toString().substring(0, 3).getBytes()[0], UUID.randomUUID().toString().substring(0, 3).getBytes()[0]) : new Point(0, 0);
    }

}
