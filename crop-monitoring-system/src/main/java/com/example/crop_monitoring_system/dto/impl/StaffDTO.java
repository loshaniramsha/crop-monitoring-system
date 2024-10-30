package com.example.crop_monitoring_system.dto.impl;

import com.example.crop_monitoring_system.dto.StaffStates;
import com.example.crop_monitoring_system.entity.Gender;
import com.example.crop_monitoring_system.entity.Role;
import com.example.crop_monitoring_system.entity.impl.FieldEntity;
import com.example.crop_monitoring_system.entity.impl.MonitoringLogEntity;
import com.example.crop_monitoring_system.entity.impl.VehicleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements StaffStates {

    private String staffId;

    private String firstName;
    private String lastName;

    private String designation;
    private Gender gender;
    private Date joiningDate;
    private Date birthDate;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;

    private String phoneNumber;
    private String email;
    private Role role;

    private List<FieldDTO> fields;
    private List<VehicleDTO> vehicles;
    private MonitoringLogDTO log;
}
