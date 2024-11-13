package com.example.crop_monitoring_system.utills;

import java.awt.*;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class AppUtil {
    private static final Random random = new Random();
    public static String convertImageToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);

    }

  public static Point generateFieldLocation() {
      int x = random.nextInt(201) - 100;  // Range: -100 to 100
      int y = random.nextInt(201) - 100;  // Range: -100 to 100
      return new Point(x, y);
  }

}
