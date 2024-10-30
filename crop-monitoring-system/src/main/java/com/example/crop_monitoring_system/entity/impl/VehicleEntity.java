package com.example.crop_monitoring_system.entity.impl;

import com.example.crop_monitoring_system.entity.States;
import com.example.crop_monitoring_system.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.Proxy;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {
    @Id
    private String vehicleCode;

    private String licensePlateNumber;
    private String vehicleType;

    @Enumerated(EnumType.STRING)
    private States state;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;

    private String remark;

}
