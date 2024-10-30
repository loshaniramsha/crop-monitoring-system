package com.example.crop_monitoring_system.entity.impl;

import com.example.crop_monitoring_system.entity.EquipmentType;
import com.example.crop_monitoring_system.entity.States;
import com.example.crop_monitoring_system.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperEntity {
    @Id
    private String equipmentId;

    private String equipmentName;

    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;

    @Enumerated(EnumType.STRING)
    private States state;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;
}
