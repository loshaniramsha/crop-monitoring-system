package com.example.crop_monitoring_system.utills;

import java.util.UUID;

public class AppUtil {
    public static String generateCropId(){
        return "CROP-"+ UUID.randomUUID();}

    public static String generateEquipmentId(){
        return "EQUIPMENT-"+ UUID.randomUUID();
    }

    public static String generateFieldId(){
        return "FIELD-"+ UUID.randomUUID();
    }
    public static String generateMonitoringLogId(){
        return "LOG-"+ UUID.randomUUID();
    }
    public static String generateStaffId(){
        return "STAFF-"+ UUID.randomUUID();
    }
    public static String generateUserId(){
        return "USER-"+ UUID.randomUUID();
    }
    public static String generateVehicleId(){
        return "VEHICLE-"+ UUID.randomUUID();
    }
}
