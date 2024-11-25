package com.example.crop_monitoring_system.dto.impl;

import com.example.crop_monitoring_system.dto.VehicleStates;
import com.example.crop_monitoring_system.entity.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements VehicleStates {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleType;
    private String fuelType;
    private String state;  // Changed to String for simplified handling
    private String staffId; // Changed to String to hold just the ID reference
    private String remark;
}
