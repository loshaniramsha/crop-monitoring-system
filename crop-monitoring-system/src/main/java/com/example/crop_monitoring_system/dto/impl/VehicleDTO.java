package com.example.crop_monitoring_system.dto.impl;

import com.example.crop_monitoring_system.dto.VehicleStates;
import com.example.crop_monitoring_system.entity.States;
import com.example.crop_monitoring_system.entity.impl.StaffEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private States state;
    private StaffDTO staff;
    private String remark;
}
