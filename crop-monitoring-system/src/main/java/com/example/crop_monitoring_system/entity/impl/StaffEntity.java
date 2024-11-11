package com.example.crop_monitoring_system.entity.impl;

import com.example.crop_monitoring_system.entity.Gender;
import com.example.crop_monitoring_system.entity.Role;
import com.example.crop_monitoring_system.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String staffId;

    private String firstName;
    private String lastName;
    private String designation;

    @Enumerated(EnumType.STRING)
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "staff_field",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private List<FieldEntity> fields;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VehicleEntity> vehicles;

    @OneToMany(mappedBy = "staff",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<EquipmentEntity> equipments;

    @ManyToOne
    @JoinColumn(name = "logId")
    private MonitoringLogEntity log;

}
